package com.wb.aop.aspect.serviceA;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 对 PlayedCounter 进行改进：采用注解方式使用切面
 * 切点方法所用表达式中的 perform 含参
 * <p>
 * 场景：节目可以点播，记录每一场节目的点播数
 */
@Aspect
public class AnnotatePlayedCounter {

    /**
     * 点播数量
     */
    private static int playedCount;

    /**
     * 切点
     * 不同点：注解式的切点
     */
    @Pointcut("@annotation(com.wb.aop.pointcut.serviceA.AnnotationPointCut)")
    public void performce() {
    }

    /**
     * 记录节目的点播数
     */
    @Before("performce()")
    public void countPlayed() {
        System.out.println("@AnnotationPointCut 被切到了，开始处理业务...");
        playedCount ++;
    }

    /**
     * 查询节目的点播数量
     */
    public static int getPlayCount() {
        return playedCount;
    }

}