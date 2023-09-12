package org.example.demo01;

import javax.naming.Name;

/**如何实现多线程操作同一个对象*/
public class TestThread4 implements Runnable {

    private int ticketNum = 10;
    @Override
    public void run() {
        while (true){
            if (ticketNum <= 0){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum-- + "票");
        }
    }

    //多个线程操作统一资源就会造成异常数据
    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();
        new Thread(testThread4, "张三").start();
        new Thread(testThread4, "李四").start();
        new Thread(testThread4, "王五").start();
    }
}
