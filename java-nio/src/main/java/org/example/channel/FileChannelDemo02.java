package org.example.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * filechannel写操作
 */
public class FileChannelDemo02 {

    public static void main(String[] args) throws IOException {

        //1、打开filechannel
        RandomAccessFile accessFile = new RandomAccessFile("test02.txt", "rw");
        FileChannel channel = accessFile.getChannel();

        //2、创建buffer对象
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String newDate = "new Data";
        buffer.clear();

        //3、写入数据
        buffer.put(newDate.getBytes());
        buffer.flip();

        //4、fileChannel完成最终实现
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

        //5、关闭
        channel.close();
    }
}
