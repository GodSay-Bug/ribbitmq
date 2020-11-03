package m3;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
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

        // 定义扇形扇出的交换机    模拟发送日志
//        c.exchangeDeclare("log", "fanout");
        c.exchangeDeclare("log", BuiltinExchangeType.FANOUT);

        // 向交换机发送消息
        while (true) {
            System.out.println("输入信息：");
            String msg = new Scanner(System.in).nextLine();
            /*
            log: 交换机的名字(简单模式和工作模式时是“”， 默认是使用的direct交换机)
            routingKey: 群发模式下，对付fanout交换机，这个参数无效；它只会向事先绑定好的消费者发送消息
            props:其他属性

             */
            c.basicPublish("log", "", null, msg.getBytes());
        }
    }
}
