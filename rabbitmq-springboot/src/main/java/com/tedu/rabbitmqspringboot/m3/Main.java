package com.tedu.rabbitmqspringboot.m3;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


/**
 * @Author WL
 * @Date 2020-11-4 11:49
 * @Version 1.0
 * 工厂模式
 * 不创建队列，创建交换机
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // 交换机
    @Bean
    public FanoutExchange logsExchange() {
        return new FanoutExchange("logs", false, false);
    }

    @Autowired
    private Producer p;

    // 手动调用生产者
    @PostConstruct  //  spring完全加载完成，扫描创建完所有对象，并完成所有注入操作后执行
    public void test() {
        /*new Thread(new Runnable(){
            @Override
            public void run() {
                p.sent();
            }
        }).start();*/

        // lambda表达式
        new Thread(() -> p.sent()).start();


    }

}
