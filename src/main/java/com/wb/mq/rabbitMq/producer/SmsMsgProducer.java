package com.wb.mq.rabbitMq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Named;

/**
 * 消息生产者处理类
 */
@Service
public class SmsMsgProducer implements ISmsMsgProducer {

    @Autowired
    @Named("amqpTemplate")
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMsg(String msg) {
        this.amqpTemplate.convertAndSend("rentSmsKey", msg);
    }
}