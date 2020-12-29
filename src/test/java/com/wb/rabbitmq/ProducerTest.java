package com.wb.rabbitmq;

import com.wb.mq.rabbitMq.producer.SmsMsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Named;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-mq-rabbit-producer.xml")
public class ProducerTest {

    @Autowired
    private SmsMsgProducer smsMsgProducer;

    @Test
    public void testProducer() {
        smsMsgProducer.sendMsg("rentSMS =========> by weiboo");
    }

}