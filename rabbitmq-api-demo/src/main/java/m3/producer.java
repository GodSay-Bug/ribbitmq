package m3;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author WL
 * @Date 2020-11-3 14:18
 * @Version 1.0
 * 工厂模式
 */
public class producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.140"); // wht6.cn
        f.setUsername("admin");
        f.setPassword("admin");
        Channel c = f.newConnection().createChannel();

        // 定义交换机    模拟发送日志
//        c.exchangeDeclare("log", "fanout");
        c.exchangeDeclare("log", BuiltinExchangeType.FANOUT);

        // 向交换机发送消息

    }
}
