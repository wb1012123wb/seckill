package com.wb.aop.aspect.serviceA;

import org.aspectj.lang.annotation.*;

/**
 * 对 Audience1 进行改进
 * 通过 @Pointcut 注解声明频繁使用的切点表达式
 */
@Aspect
public class Audience2 {

    @Pointcut("execution(* com.wb.aop.pointcut.serviceA.Performance.perform(..))")
    public void performce() {
    }

    /**
     * 演出之前：就座
     */
    @Before("performce()")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    /**
     * 演出之前：手机调至静音
     */
    @Before("performce()")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    /**
     * 表演成功后：鼓掌喝彩
     */
    @AfterReturning("performce()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    /**
     * 对表演不满意：要求退款
     */
    @AfterThrowing("performce()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }

}