package org.example.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

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

            channel.basicConsume(queueName, new DeliverCallback() {
                @Override
                public void handle(String s, Delivery delivery) throws IOException {
                    System.out.println("收到的消息是：" + new String(delivery.getBody(), "UTF-8"));
                }
            }, new CancelCallback() {
                @Override
                public void handle(String s) throws IOException {
                    System.out.println("接受失败了");
                }
            });
            System.out.println("消息接受成功");
            System.in.read();
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
