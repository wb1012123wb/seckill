package com.wb.aop.pointcut.serviceA;

import java.lang.annotation.*;

/**
 * 用于切面的annotation切点
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AnnotationPointCut {
}
