package com.wb.exception;

/**
 * 重复秒杀异常（运行期异常）
 * Spring声明式异常只接收运行期异常，对于非运行期异常不会做回滚操作
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
