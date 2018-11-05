package com.wb.service;

import com.wb.dto.Exposer;
import com.wb.dto.SeckillExecution;
import com.wb.entity.Seckill;
import com.wb.exception.RepeatKillException;
import com.wb.exception.SeckillClosedException;
import com.wb.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在“使用者”的角度设计接口
 * 三个方面：方法定义的粒度、参数（越简练越直接越好）、返回类型（return类型/异常）
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀接口
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址（与业务不相关）
     * 否则输出系统时间和秒杀时间
     * 防止用户按照规则拼出秒杀地址作弊
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillClosedException;

    /**
     * 执行秒杀操作by声明式事务
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);

}
