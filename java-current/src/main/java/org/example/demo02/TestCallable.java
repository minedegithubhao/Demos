package org.example.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * 创建线程方式三：实现callable接口
 * 可以有返回值
 * 可以抛出异常
 */
public class TestCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, name);
        System.out.println("下载的文件名为：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable thread1 = new TestCallable("https://pic.nximg.cn/file/20160910/2656254_163933716037_2.jpg", "thread1大树.jpg");
        TestCallable thread2 = new TestCallable("https://pic.nximg.cn/file/20160910/2656254_163933716037_2.jpg", "thread2大树.jpg");
        TestCallable thread3 = new TestCallable("https://pic.nximg.cn/file/20160910/2656254_163933716037_2.jpg", "thread3大树.jpg");
        //1、创建执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //2、提交执行
        Future<Boolean> r1 = executorService.submit(thread1);
        Future<Boolean> r2 = executorService.submit(thread2);
        Future<Boolean> r3 = executorService.submit(thread3);
        //3、获取结果
        Boolean rs1 = r1.get();
        Boolean rs2 = r2.get();
        Boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        //4、关闭服务
        executorService.shutdownNow();
    }
}
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