package com.tedu.rabbitmqspringboot.m5;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

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
        while (true) {
            System.out.println("输入：");
            String msg = new Scanner(System.in).nextLine();
            System.out.println("输入路由键：");
            String key = new Scanner(System.in).nextLine();

            // 默认是持久消息 向交换机发送消息        路由键为空
            t.convertAndSend("topic_logs", key, msg);



        }
    }
}
