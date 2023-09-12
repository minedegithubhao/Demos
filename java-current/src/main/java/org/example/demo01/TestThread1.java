package org.example.demo01;

/**
 * 创建线程的方式一：继承Thread类，重写run()方法，调用start()方法开启线程
 */
public class TestThread1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程--" + i);
        }
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        testThread1.start();

        for (int i = 0; i < 200; i++) {
            System.out.println("我在看代码--" + i);
        }
    }
}
