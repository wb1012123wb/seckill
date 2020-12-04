package com.wb.aop.aspect.serviceA;

import com.wb.annotation.MyAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 对 Audience2 进行改进
 * 用 @Around 代替 takeSeats + silenceCellPhones + applause + demandRefund
 */
@Aspect
public class Audience3 {

    /**
     * 被通知的方法
     */
    @Pointcut("execution(* com.wb.aop.pointcut.serviceA.Performance.perform(..))")
    public void performce() {
    }


    /**
     * 观看演出：相当于 takeSeats + silenceCellPhones + applause + demandRefund
     *
     * @param jp 在 @Around 中通过 ProceedingJoinPoint 来调用被通知的方法 watchPerformance()，
     *           处理完逻辑后，当要将控制权交给被通知的方法时，需要调用 ProceedingJoinPoint 的 proceed() 方法。
     *           不调用 proceed() 方法：会阻塞对被通知方法的调用
     *           多次调用 proceed() 方法：可以作为重试逻辑的调用场景，即：在被通知方法失败后，进行重复尝试
     */
    @Around("performce()")
    public void watchPerformance(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        if (annotation == null) {
            jp.proceed();
        }

        try {
            System.out.println("Silencing cell phones");
            System.out.println("Taking seats");
            jp.proceed();
            System.out.println("CLAP CLAP CLAP!!!");
        } catch (Throwable e) {
            System.out.println("Demanding a refund");
        }
    }

}