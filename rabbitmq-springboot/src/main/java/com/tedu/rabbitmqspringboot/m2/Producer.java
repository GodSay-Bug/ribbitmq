package com.tedu.rabbitmqspringboot.m2;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
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
            // 默认是持久消息
            t.convertAndSend("task_Queue", msg);

            // 如果要发送非持久消息，需要一个消息预处理对象先修改消息属性，然后再进行发送
            /*t.convertAndSend("task_Queue", (Object) msg, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties messages = message.getMessageProperties();// 调整为非持久
                    messages.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
                    return message;

                }
            });*/


        }
    }
}
