package m3;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-11-3 14:33
 * @Version 1.0
 * 1.定义队列
 * 2.定义交换机
 * 3.绑定
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.140"); // wht6.cn
        f.setUsername("admin");
        f.setPassword("admin");
        Channel c = f.newConnection().createChannel();

        // 定义队列
        String queue = UUID.randomUUID().toString();
        c.queueDeclare(queue, false, true, true, null);
        // 定义交换机
        c.exchangeDeclare("log", BuiltinExchangeType.FANOUT);
        // 绑定
        c.queueBind(queue, "log", "");

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String msg = new String(message.getBody());
                System.out.println("收到：" + msg);

            }
        };

        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        };

        // 从queue队列消费消息
        c.basicConsume(queue, true, deliverCallback, cancelCallback);
    }
}
