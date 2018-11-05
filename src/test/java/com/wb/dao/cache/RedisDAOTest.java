package com.wb.dao.cache;

import com.wb.dao.SeckillDAO;
import com.wb.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDAOTest {

    private long id = 1001;

    @Autowired
    private SeckillDAO seckillDAO;

    @Autowired
    private RedisDAO redisDAO;

    @Test
    public void Seckill() {
        //get and put
        Seckill seckill = redisDAO.getSeckill(id);
        if (seckill == null) {
            Seckill result = seckillDAO.queryById(id);
            if (result != null) {
                redisDAO.putSeckill(result);
                System.out.println(result);
                redisDAO.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }
}