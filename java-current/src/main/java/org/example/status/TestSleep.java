package org.example.status;

import org.example.demo01.TestThread4;

public class TestSleep implements Runnable {

    private int ticketNum = 10;
    @Override
    public void run() {
        while (true){
            if (ticketNum <= 0){
                break;
            }
            try {
                // 模拟网络延迟，放大问题的发生
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum-- + "票");
        }
    }

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();
        new Thread(testThread4, "张三").start();
        new Thread(testThread4, "李四").start();
        new Thread(testThread4, "王五").start();
    }
}
