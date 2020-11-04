package com.tedu.rabbitmqspringboot.m1;

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
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @Bean
    public Queue helloworldQueue() {
        return new Queue("helloworld", false);// 非持久
    }

    @Autowired
    private Producer p;

    // 手动调用生产者
    @PostConstruct  //  spring完全加载完成，扫描创建完所有对象，并完成所有注入操作后执行
    public void test() {
        p.sent();
    }

}
