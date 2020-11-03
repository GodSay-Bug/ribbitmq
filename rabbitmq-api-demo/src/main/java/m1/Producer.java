package m1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-10-27 17:30
 * @Version 1.0
 * 简单模式
 */

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.140");
        f.setPort(5672);
        f.setUsername("admin");
        f.setPassword("admin");

        Connection conn = f.newConnection();    //网络连接
        Channel c = conn.createChannel();   //  通信通道

        //  定义队列，服务器不存在这个队列，就新建队列；不然就直接使用已存在的队列
        c.queueDeclare("helloworld",
                false,      // 是否是持久队列
                false,      // 是否是排他度列    能被多个用户获取，就是排他
                false,      // 是否自动删除
                null);
        // 发送消息                                                    // 发送的消息必须是字节
        c.basicPublish("",      // 第三种模式才使用到
                "helloworld",
                null,               // 针对发送的这条消息，可以添加一些键值对的属性数据
                "Hello World!".getBytes());
        System.out.println("消息已发送");
    }

}
