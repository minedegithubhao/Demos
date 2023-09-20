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
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
        clientChannel.configureBlocking(false);

        Thread readThread = new Thread(() -> {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                while (true) {
                    int bytesRead = clientChannel.read(buffer);
                    if (bytesRead == -1) {
                        System.out.println("Server has disconnected.");
                        clientChannel.close();
                        break;
                    }
                    String message = new String(buffer.array(), 0, bytesRead);
                    System.out.println("Received: " + message);
                    buffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        readThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("exit")) {
                clientChannel.close();
                break;
            }
            clientChannel.write(ByteBuffer.wrap(message.getBytes()));
        }
    }
}
