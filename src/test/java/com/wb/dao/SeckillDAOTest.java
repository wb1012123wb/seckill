package com.wb.dao;

import com.wb.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 配置spring和junit整合：junit启动时加载springIOC容器
 * spring-test, junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDAOTest {

    // 注入DAO实现类依赖
    @Resource
    private SeckillDAO seckillDAO;

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDAO.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * Error querying database.
         * Cause: org.springframework.jdbc.CannotGetJdbcConnectionException:
         * Could not get JDBC Connection;
         * nested exception is java.sql.SQLException:
         * An attempt by a client to checkout a Connection has timed out.
         */
    }

    @Test
    public void queryAll() {
    }

    @Test
    public void reduceNumber() {

    }
}