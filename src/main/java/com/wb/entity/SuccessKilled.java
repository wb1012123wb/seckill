package com.wb.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SuccessKilled {

    // 秒杀商品id
    private long seckillId;

    // 用户手机号
    private long userPhone;

    // 状态
    private short state;

    // 创建时间
    private Date createTime;

    // 一个秒杀seckill实体对应多个successKilled实体：一个秒杀商品供多人秒杀
    private Seckill seckill;

}
