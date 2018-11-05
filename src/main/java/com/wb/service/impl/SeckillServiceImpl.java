package com.wb.service.impl;

import com.wb.dao.SeckillDAO;
import com.wb.dao.SuccessKilledDAO;
import com.wb.dao.cache.RedisDAO;
import com.wb.dto.Exposer;
import com.wb.dto.SeckillExecution;
import com.wb.entity.Seckill;
import com.wb.entity.SuccessKilled;
import com.wb.enums.SeckillStateEnum;
import com.wb.exception.RepeatKillException;
import com.wb.exception.SeckillClosedException;
import com.wb.exception.SeckillException;
import com.wb.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDAO seckillDAO;
    @Autowired
    private SuccessKilledDAO successKilledDAO;
    @Autowired
    private RedisDAO redisDAO;

    //md5盐值字符串，用于混淆md5
    private final String salt = "sldjfi0w-4mlkfk[k-34tkgmfedk;kj4-[kgmkls;kdjfq";

    public List<Seckill> getSeckillList() {
        return seckillDAO.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDAO.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        /**
         * 思路：
         * 秒杀存不存在
         * 秒杀过没过时
         * 可以开启秒杀的话暴露什么信息
         */
        // 优化点：redis缓存优化，一致性维护：建立在超时的基础上
        // 1：访问redis
        Seckill seckill = redisDAO.getSeckill(seckillId);
        if (seckill == null) {
            // 2：访问数据库
            seckill = seckillDAO.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                // 3：加入redis
                redisDAO.putSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        // 当前系统时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) { // 不在开启时间内
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        // 转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);   // 开启的话，暴露md5和秒杀id
    }

    /**
     * 使用注解控制事务的优点：
     * 1：开发团队达成一致约定，明确标注事务方法的编程风格
     * 2：保证事务方法的执行时间尽可能短，不要穿插其他的网络操作（比如缓存、http，毫秒级对于并发量的应用来说很重）或者剥离到事务方法外部。
     * 3：不是所有的方法都需要事务，如：只有一条修改操作，或者只读操作，不需要事务控制
     *    关注mysql行级锁
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillClosedException {
        // 核对md5
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        // 执行秒杀逻辑：减库存 + 记录购买行为
        Date date = new Date();
        try {
            // 记录购买行为。秒杀成功后再次秒杀会忽略插入，返回0（ignore的存在）
            int insertCount = successKilledDAO.insertSuccessKilled(seckillId, userPhone);
            // 唯一验证：seckillId、userPhone
            if (insertCount <= 0) {
                // 重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                // 减库存。热点商品竞争
                int updateCount = seckillDAO.reduceNumber(seckillId, date);
                if(updateCount <= 0 ) {
                    // 没有更新到记录，秒杀结束，rollback
                    throw new SeckillClosedException("seckill is closed");
                } else {
                    // 秒杀成功，commit
                    SuccessKilled successKilled = successKilledDAO.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillClosedException e1){
            throw e1;
        } catch (RepeatKillException e2){
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 所有编译器异常转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {
        if (md5 == null && md5.equals(getMD5(seckillId))) {
            return new SeckillExecution(seckillId, SeckillStateEnum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        // 执行存储过程后，result被赋值
        try {
            seckillDAO.killByProcedure(map);
            //获取result
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                // 秒杀成功
                SuccessKilled sk = successKilledDAO.queryByIdWithSeckill(seckillId, userPhone);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;// TODO: 2018/3/24
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());//  Spring的md5加密工具
    }
}