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
        // 启selector,并注册accept事件
        Selector selector = Selector.open();
        // 打开ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // ServerSocketChannel绑定端口
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 向选择器注册，并告诉选择器监听该通道的OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Chat Server is running on port " + PORT);

        while (true) {
            // 阻塞等待2s，监听所有通道
            int readyChannels = selector.select(2000);
            System.out.println("等待链接");
            // readyChannels = 0，则说明未见听到就绪的事件
            if (readyChannels == 0) {
                System.out.println("未发现链接");
                continue;
            }

            // 监听到就绪的事件，则获取已就绪事件的键集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            // 遍历就绪时间
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // 判断该SelectionKey对应的channel是否可以接受客户端连接
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
        // 通过SelectionKey获取channel
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        // 根据服务端通道ServerSocketChannel获取对应的客户端通道SocketChannel
        SocketChannel clientChannel = serverChannel.accept();
        // 设置客户端通道为非阻塞模式
        clientChannel.configureBlocking(false);
        // 向选择器注册可读事件
        clientChannel.register(selector, SelectionKey.OP_READ);
        // 打印客户端连接信息
        System.out.println("Client connected: " + clientChannel.getRemoteAddress());
    }

    private static void handleReadable(SelectionKey key, Selector selector) throws IOException {
        // 通过SelectionKey获取channel
        SocketChannel clientChannel = (SocketChannel) key.channel();
        // 创建缓冲区，并设置缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        // 从缓冲区中读取数据
        int bytesRead = clientChannel.read(buffer);
        // bytesRead = -1 说明缓冲区中没有数据
        if (bytesRead == -1) {
            key.cancel();
            clientChannel.close();
            System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
            return;
        }
        // 缓冲区有数据则读取所有数据
        String message = new String(buffer.array(), 0, bytesRead);
        System.out.println("Received from " + clientChannel.getRemoteAddress() + ": " + message);

        // 将接收到的数据广播给所有的channel
        broadcastMessage(selector, clientChannel, message);
    }

    private static void broadcastMessage(Selector selector, SocketChannel sender, String message) throws IOException {
        // 获取selector中注册的所有channel
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            if (key.isValid() && key.channel() instanceof SocketChannel
                    // 广播的channel中不包括发送者
                    && key.channel() != sender) {

                //根据SelectionKey获取对应的channel
                SocketChannel clientChannel = (SocketChannel) key.channel();
                // 将message写入到缓冲区
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                // 将数据写入到buffer中
                clientChannel.write(buffer);
            }
        }
    }

}
