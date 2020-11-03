package m2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-10-28 17:20
 * @Version 1.0
 * 合理的分发消息
 * 手动ack
 * qos=1
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 简历连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.140");
        f.setPort(5672);    // 默认端口可以省略
        f.setUsername("admin");
        f.setPassword("admin");

        Channel c = f.newConnection().createChannel();
        //    为什么消费者也要定义队列：因为不管谁先启动，都能创建队列，保证队列的存在
        c.queueDeclare("task_queue", true, false, false, null);

        //
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String msg = new String(message.getBody());
                System.out.println("收到消息=" + msg);
                // 遍历所有字符，如果有 。  就暂停1s
                for (int i = 0; i < msg.length(); i++) {
                    if (msg.charAt(i) == '.') {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                // 向服务器发送回执
                //  deliveryTag：回执
                // multiple: 是否同时确认之前收到的所有信息（一般就一条）
                c.basicAck(message.getEnvelope().getDeliveryTag(), false);

                System.out.println("消息处理完成==================");

            }
        };

        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        };

        // 每次只能接收一条消息
        c.basicQos(1);

        // false 手动分配，
        // 消费数据
        c.basicConsume("task_queue", false, deliverCallback, cancelCallback);

    }
}
