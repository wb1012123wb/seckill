package com.wb.dao;

import com.wb.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface SuccessKilledDAO {

    /**
     * 插入购买明细，可根据联合主键过滤重复
     * @param seckilled
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckilled") long seckilled, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SeccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @PathVariable("userPhone") long userPhone);

}
