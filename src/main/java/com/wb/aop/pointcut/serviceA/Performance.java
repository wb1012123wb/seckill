package com.wb.aop.pointcut.serviceA;

/**
 * 切点
 */
public interface Performance {
    /**
     * 无参
     */
    void perform();

    /**
     * 含参
     * @param programmeId 节目ID
     */
    void perform(String programmeId);
}