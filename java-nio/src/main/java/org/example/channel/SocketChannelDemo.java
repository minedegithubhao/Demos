package org.example.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class SocketChannelDemo {

    public static void main(String[] args) throws IOException {
        // 创建socketChannel方式一
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
//        // 创建socketChannel方式二
//        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));

        // 链接检验
        // 测试设置阻塞还是非阻塞
        channel.configureBlocking(false);

        // 读操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(scanner.next());
            byteBuffer.put(scanner.next().getBytes());
            channel.read(byteBuffer);
        }

        channel.read(byteBuffer);
        channel.close();
        System.out.println("读取结束");
    }
}
