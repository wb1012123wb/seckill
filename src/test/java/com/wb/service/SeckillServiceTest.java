package com.wb.service;

import com.wb.dto.Exposer;
import com.wb.dto.SeckillExecution;
import com.wb.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);

    }
    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        /**
         * Exposer{
         * exposed=true,
         * md5='3edfed987134989ac581b0433f7eb739',
         * seckillId=1000,
         * now=0,
         * start=0,
         * end=0}
         */
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 18636345802L;
        String md5 = "3edfed987134989ac581b0433f7eb739";
        SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
        logger.info("seckillExecution={}", seckillExecution);
    }
}