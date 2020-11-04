package com.tedu.rabbitmqspringboot.m3;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author WL
 * @Date 2020-11-4 11:51
 * @Version 1.0
 */
@Component
public class Consumer {

    /*
    创建随机队列，非持久，独占，自动删除
    把这个队列绑定到logs交换机
     */


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,     // 指定队列,不指定的话就是随机命名
            exchange = @Exchange(name = "logs", declare = "false")     // 交换机 false=不重复定义交换机
    ))     // 直接加到方法上,不需要RabbitHandler了
    public void receive1(String msg) {
        System.out.println("1收到:" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,     // 指定队列,不指定的话就是随机命名
            exchange = @Exchange(name = "logs", declare = "false")     // 交换机
    ))
    public void receive2(String msg) {
        System.out.println("2收到:" + msg);
    }
}
