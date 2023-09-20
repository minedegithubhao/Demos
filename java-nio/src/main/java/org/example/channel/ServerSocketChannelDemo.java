package org.example.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author ASUS
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws Exception {

        //监听的端口号
        int port = 8888;

        //定义buffer
        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());

        //ServerSocketChannel打开监听
        ServerSocketChannel ssc = ServerSocketChannel.open();

        //绑定端口
        ssc.socket().bind(new InetSocketAddress(port));

        //设置非阻塞模式
        ssc.configureBlocking(false);

        //循环监听是否有链接传入
        while (true) {
            System.out.println("等待链接。。。");

            //如果没有链接传入accept()将返回null
            SocketChannel channel = ssc.accept();
            if (channel == null) {
                System.out.println("没有链接传入");
                Thread.sleep(2000);
            } else {
                System.out.println("接入链接" + channel.socket().getRemoteSocketAddress());

                //指针归0
                buffer.rewind();

                //讲channel中的数据写入buffer
                channel.write(buffer);
                System.out.println("接收到：" + buffer.getChar());

                //关闭channel
                channel.close();
                ssc.close();
            }
        }

    }
}
