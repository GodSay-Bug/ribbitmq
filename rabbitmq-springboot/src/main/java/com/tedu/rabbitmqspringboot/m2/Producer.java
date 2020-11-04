package com.tedu.rabbitmqspringboot.m2;

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
            System.out.println("输入");
            String msg = new Scanner(System.in).nextLine();
            t.convertAndSend("task_Queue", msg);
        }
    }
}
