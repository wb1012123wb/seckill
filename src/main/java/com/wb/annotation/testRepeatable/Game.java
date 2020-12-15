package com.wb.annotation.testRepeatable;

import java.lang.annotation.*;

/**
 * 2. 游戏注解【一个人可以玩多个游戏】
 */
@Repeatable(People.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Game {
    String value() default "";
}