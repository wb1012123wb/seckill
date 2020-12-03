package com.wb.annotation.testRepeatable;

import java.lang.annotation.*;

/**
 * 2. 游戏注解
 */
@Repeatable(People.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Game {
    String value() default "";
}