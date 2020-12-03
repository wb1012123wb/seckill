package com.wb.annotation.bankServer;

import java.lang.annotation.*;

/**
 * 定义限额注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BankTransferMoneyCheck {
    double maxMoney() default 10000;
}