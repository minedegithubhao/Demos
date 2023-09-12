package org.example.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 练习thread，实现多线程同步下载图片
 */
public class TestThread2 extends Thread {

    /**网络图片地址*/
    private String url;
    /**保存的文件名称*/
    private String name;

    public TestThread2(String url, String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownLoader downLoader = new WebDownLoader();
        downLoader.downLoader(url, name);
        System.out.println("下载的文件名为：" + name);
    }

    public static void main(String[] args) {
        TestThread2 thread1 = new TestThread2("https://pic.nximg.cn/file/20160910/2656254_163933716037_2.jpg", "thread1大树.jpg");
        TestThread2 thread2 = new TestThread2("https://pic.nximg.cn/file/20160910/2656254_163933716037_2.jpg", "thread2大树.jpg");
        TestThread2 thread3 = new TestThread2("https://pic.nximg.cn/file/20160910/2656254_163933716037_2.jpg", "thread3大树.jpg");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

//下载器
class WebDownLoader{
    //下载方法
    public void downLoader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
