package com.wb.mq.rabbitMq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 消息消费者处理类
 */
public class SmsMsgConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("消费者消费的消息是：============>" + message);
    }
}
