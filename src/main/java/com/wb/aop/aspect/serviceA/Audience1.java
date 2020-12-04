package com.wb.aop.aspect.serviceA;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 切面：观众
 * 应用：演出 = 观众[切面] + 表演[切点]
 * 定义4个动作：观众就座、观众将手机调至静音、鼓掌喝彩、不满意要求退票
 *
 * @Aspect Spring 的 AspectJ 自动代理仅仅使用 @AspectJ 作为创建切面的指导，切面依然是基于代理的。
 *
 * <p>
 * AspectJ 提供了五个注解来定义通知：
 * @After 通知方法会在目标方法返回或抛出异常后调用
 * @AfterReturning 通知方法会在目标方法返回后调用
 * @AfterThrowing 通知方法会在目标方法抛出异常后调用
 * @Around 通知方法会将目标方法封装起来【最强大。@Around = @Before + @After】
 * @Before 通知方法会在目标方法调用之前执行
 * </p>
 */
@Aspect
public class Audience1 {

    /**
     * 演出之前：就座
     */
    @Before("execution(* com.wb.aop.pointcut.serviceA.Performance.perform(..))")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    /**
     * 演出之前：手机调至静音
     */
    @Before("execution(* com.wb.aop.pointcut.serviceA.Performance.perform(..))")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    /**
     * 表演成功后：鼓掌喝彩
     */
    @AfterReturning("execution(* com.wb.aop.pointcut.serviceA.Performance.perform(..))")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    /**
     * 对表演不满意：要求退款
     */
    @AfterThrowing("execution(* com.wb.aop.pointcut.serviceA.Performance.perform(..))")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }
}

/**
 * 相同的切点表达式 @Before("execution(** concert.Performance.perform(..))") 重复了多遍
 * 改进：只定义一次切点，需要的时候再引用 → @Pointcut
 */