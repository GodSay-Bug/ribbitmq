package m5;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-11-3 15:30
 * @Version 1.0
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.140"); // wht6.cn
        f.setUsername("admin");
        f.setPassword("admin");
        Channel c = f.newConnection().createChannel();

        // 定义队列    无参方法让服务器自动命名，其他的参数也有默认属性；false,true,true 非持久，独占，自动删除
        String queue = c.queueDeclare().getQueue();
        // 定义交换机
        c.exchangeDeclare("topic_logs", BuiltinExchangeType.TOPIC);
        // 定义绑定建
        System.out.println("输入绑定建，用空格隔开：");
        String s = new Scanner(System.in).nextLine();
        String[] r = s.split("\\s+");
        for (String key : r) {
            c.queueBind(queue, "topic_logs", key);
        }

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String msg = new String(message.getBody());
                long key = message.getEnvelope().getDeliveryTag();
                System.out.println(key + "-" + msg);

            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        };

        c.basicConsume(queue, true, deliverCallback, cancelCallback);

    }
}
