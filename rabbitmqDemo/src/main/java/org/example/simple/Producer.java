package org.example.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) {
        // 1、创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("123.56.126.222");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            // 2、创建链接Connection
            connection = connectionFactory.newConnection("生产者");
            // 3、通过链接获取通道Channel
            channel = connection.createChannel();
            // 4、通过通道船舰交换机、队列、绑定关系、路由key、发送消息、和接受消息
            String queueName = "queue1";
            /*
             * 参数1：队列的名称
             * 参数2：是否持久化
             * 参数3：排他性，是否独占
             * 参数4：是否自动删除，最后一个消息消费完成后，是否删除队列
             * 参数5：携带附属参数
             */
            channel.queueDeclare(queueName, false, false, false, null);
            // 5、准备消息内容
            String message = "Hello World!";
            // 6、发送消息给队列queue
            channel.basicPublish("", queueName, null, message.getBytes());

            System.out.println("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、关闭链接
            if(channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
            // 8、关闭通道
            if (connection != null && connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
