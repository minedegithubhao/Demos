package org.example.TestPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool {


    public static void main(String[] args) {
        //创建服务，创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //执行
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());

        //关闭链接
        executorService.shutdownNow();
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
