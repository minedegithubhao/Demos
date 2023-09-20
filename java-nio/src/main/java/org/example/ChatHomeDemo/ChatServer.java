package org.example.ChatHomeDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static java.nio.channels.Selector.*;

/**
 * 聊天室服务端
 */
public class ChatServer {
    private static final int PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        // 创建选择器
        Selector selector = Selector.open();
        // 打开ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // ServerSocketChannel绑定端口
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 向选择器注册接受连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Chat Server is running on port " + PORT);

        while (true) {
            // 阻塞等待就绪事件
            int readyChannels = selector.select(2000);
            System.out.println("等待链接");
            if (readyChannels == 0) {
                System.out.println("未发现链接");
                continue;
            }

            // 发现链接，获取已就绪事件的键集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            // 遍历链接
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // 判断该SelectionKey可以接受客户端连接
                if (key.isAcceptable()) {
                    // 处理连接
                    handleAcceptable(key, selector);
                    // 处理可读事件
                } else if (key.isReadable()) {
                    handleReadable(key, selector);
                }
                // 移除已处理的键
                keyIterator.remove();
            }
        }
    }

    private static void handleAcceptable(SelectionKey key, Selector selector) throws IOException {
        // 通过key获取ServerSocketChannel
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        // 获取客户端未非阻塞模式
        SocketChannel clientChannel = serverChannel.accept();
        // 设置客户端通道为非阻塞模式
        clientChannel.configureBlocking(false);
        // 向选择器注册可读事件
        clientChannel.register(selector, SelectionKey.OP_READ);
        // 打印客户端连接信息
        System.out.println("Client connected: " + clientChannel.getRemoteAddress());
    }

    private static void handleReadable(SelectionKey key, Selector selector) throws IOException {
        //
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1) {
            key.cancel();
            clientChannel.close();
            System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
            return;
        }
        String message = new String(buffer.array(), 0, bytesRead);
        System.out.println("Received from " + clientChannel.getRemoteAddress() + ": " + message);

        // Broadcast the message to all connected clients
        broadcastMessage(selector, clientChannel, message);
    }

    private static void broadcastMessage(Selector selector, SocketChannel sender, String message) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            if (key.isValid() && key.channel() instanceof SocketChannel && key.channel() != sender) {
                SocketChannel clientChannel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                clientChannel.write(buffer);
            }
        }
    }

}
