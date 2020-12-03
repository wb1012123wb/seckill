package com.wb.annotation.testInherited;

import java.lang.annotation.*;

/**
 * 用于属性字段的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Age {

    int value() default -1;

}