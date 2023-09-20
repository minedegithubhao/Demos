package org.example.ChatHomeDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 聊天室客户端
 */
public class ChatClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        // 创建channel，并配置服务端的连接信息
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
        // 将channel设置为非阻塞
        clientChannel.configureBlocking(false);

        // 创建一个线程，不断的从buffer中读取数据
        Thread readThread = new Thread(() -> {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                while (true) {
                    // 将buffer中的数据读取到channel中
                    int bytesRead = clientChannel.read(buffer);
                    // 如果bytesRead = -1 说明
                    if (bytesRead == -1) {
                        System.out.println("Server has disconnected.");
                        clientChannel.close();
                        break;
                    }
                    String message = new String(buffer.array(), 0, bytesRead);
                    System.out.println("Received: " + message);
                    // 为防止刷屏，这里设置成每5秒钟从buffer中读取一次
                    Thread.sleep(5000);
                    // 每次读取完成后清空buffer
                    buffer.clear();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        readThread.start();

        // 从键盘录入
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("exit")) {
                clientChannel.close();
                break;
            }
            // 往buffer中写入数据
            clientChannel.write(ByteBuffer.wrap(message.getBytes()));
        }
    }
}
