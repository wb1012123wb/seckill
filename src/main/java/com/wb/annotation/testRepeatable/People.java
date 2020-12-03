package com.wb.annotation.testRepeatable;

import java.lang.annotation.*;

/**
 * 1. 玩家注解
 * 一个People可玩多个Game，在@Game上通过@Repeatable绑定People后，People的属性需要与Game的属性形成一对多的关系。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface People {

    Game[] value();

}
