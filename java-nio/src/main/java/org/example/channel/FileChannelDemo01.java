package org.example.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * file读操作
 * @author ASUS
 */
public class FileChannelDemo01 {
    /**
     * fileChannel读取数据到buffer重
     */
    public static void main(String[] args) throws IOException {

        //step1 创建fileChannel
        RandomAccessFile accessFile = new RandomAccessFile("test.txt", "rw");
        FileChannel channel = accessFile.getChannel();
        //step2 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //step3 读取数据到buffer中
        int byteRead = channel.read(buffer);

        //byteRead = -1 说明读取完了数据
        while (byteRead != -1){
            System.out.println("读取了：" + byteRead);
            //由于上面已经将channel中的数据读入到了buffer中，接下来，我们从buffer中去取，看能否取出来，作为验证
            //所以先将buffer由写入转化位读取
            buffer.flip();
            //buffer.hasRemaining()用来判断buffer中是否还有数据，如果有我们就进行读取操作
            while (buffer.hasRemaining()){
                System.out.println((char) buffer.get());
            }
            //清空buffer
            buffer.clear();
            byteRead = channel.read(buffer);
        }
        channel.close();
        accessFile.close();
        System.out.println("结束了");
    }
}
