package com.tedu.rabbitmqspringboot.m1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author WL
 * @Date 2020-11-4 11:51
 * @Version 1.0
 * 生产者
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate t;

    public void sent() {
        t.convertAndSend("helloworld", "Hello World");
    }
}
