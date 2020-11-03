package m1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-10-28 17:20
 * @Version 1.0
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
        // 定义队列
        c.queueDeclare("helloworld", false, false, false, null);

        // 消息回调对象
        DeliverCallback deliverCallback = new DeliverCallback() {

            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
//                String msg = new String(message.getBody(), "UTF-8");
//                String routhigKey = message.getEnvelope().getRoutingKey();
//                System.out.println("收到" + routhigKey + "-" + msg);
                byte[] a = message.getBody();
                String msg = new String(a);
                System.out.println("收到" + msg);

            }
        };
        //  取消回调对象
        CancelCallback cancelCallback = new CancelCallback() {

            @Override
            public void handle(String consumerTag) throws IOException {

            }
        };


        // 从队列接受数据（消费数据）
        c.basicConsume("helloworld", true, deliverCallback, cancelCallback);

    }
}
