package com.tedu.rabbitmqspringboot.m1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author WL
 * @Date 2020-11-4 11:51
 * @Version 1.0
 */
@Component
public class Consumer {

    @RabbitListener(queues = "helloworld")     // 直接加到方法上,不需要RabbitHandler了
    public void receive(String msg){
        System.out.println("收到:"+msg);
    }
}
