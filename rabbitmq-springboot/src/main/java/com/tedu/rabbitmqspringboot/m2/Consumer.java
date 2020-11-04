package com.tedu.rabbitmqspringboot.m2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author WL
 * @Date 2020-11-4 11:51
 * @Version 1.0
 */
@Component
public class Consumer {

    @RabbitListener(queues = "task_Queue")     // 直接加到方法上,不需要RabbitHandler了
    public void receive1(String msg) {
        System.out.println("1收到:" + msg);
    }

    @RabbitListener(queues = "task_Queue")     // 直接加到方法上,不需要RabbitHandler了
    public void receive2(String msg) {
        System.out.println("2收到:" + msg);
    }
}
