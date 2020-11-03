package m5;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-11-3 15:18
 * @Version 1.0
 * 主题模式
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {

        // 连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.140"); // wht6.cn
        f.setUsername("admin");
        f.setPassword("admin");
        Channel c = f.newConnection().createChannel();
        // 定义交换机
        c.exchangeDeclare("topic_logs", BuiltinExchangeType.TOPIC);

        // 向交换机发送消息，并携带路由键
        while (true) {
            System.out.println("输入消息：");
            String msg = new Scanner(System.in).nextLine();
            System.out.println("输入路由键:");
            String key = new Scanner(System.in).nextLine();
            /*
            key： 路由键，和绑定建进行匹配，将消息路由到匹配的队列
             */
            c.basicPublish("topic_logs", key, null, msg.getBytes());
        }

    }
}
