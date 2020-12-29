package com.wb.mq.rabbitMq.producer;

public interface ISmsMsgProducer {

    /**
     * 发送消息
     *
     * @param msg
     */
    void sendMsg(String msg);

}