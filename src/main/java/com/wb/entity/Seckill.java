package com.wb.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Seckill {

    // 商品库存id
    private long seckillId;

    // 商品名称
    private String name;

    // 库存数量
    private int number;

    // 秒杀开始时间
    private Date startTime;

    // 秒杀结束时间
    private Date endTime;

    // 创建时间,默认值：系统当前时间
    private Date createTime;

}
