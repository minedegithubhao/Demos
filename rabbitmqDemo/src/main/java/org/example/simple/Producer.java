package org.example.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static void main(String[] args) {
        // 1、创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("");
        connectionFactory.setPort(15672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");

        Connection connection = null;
        try {
            // 2、创建链接Connection
            connection = connectionFactory.newConnection("生产者");
            // 3、通过链接获取通道Channel
            Channel channel = connection.createChannel();
            /**
             *
             */
            channel.queueDeclare("queue1", false, false, false, null);

        } catch (Exception e){
            e.printStackTrace();
        } finally {

        }
        // 4、通过通道船舰交换机、队列、绑定关系、路由key、发送消息、和接受消息
        // 5、准备消息内容
        // 6、发送消息给队列queue
        // 7、关闭链接
        // 8、关闭通道
    }

}
