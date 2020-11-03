package m2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-11-2 16:03
 * @Version 1.0
 * 生产者
 * 合理的分发消息
 * * 手动ack
 * * qos=1
 * 工作模式
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        // 连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.140"); // wht6.cn
        f.setUsername("admin");
        f.setPassword("admin");
        Channel c = f.newConnection().createChannel();
        // 定义队列
        // 服务器已存在的队列的属性不能修改，否则会出错
        c.queueDeclare(
                "task_queue",
                true,       //  队列持久化
                false,
                false,
                null);
        // 发送消息
        while (true) {
            System.out.print("输入消息： ");
            String msg = new Scanner(System.in).nextLine();
            c.basicPublish(
                    "",
                    "task_queue",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,       // 消息持久化
                    msg.getBytes());
        }
    }
}