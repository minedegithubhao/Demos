package org.example.priority;

public class TestPriority {

    public static void main(String[] args) {
        // 主线程的优先级
        System.out.println(Thread.currentThread().getName() + "---" + Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority, "thread1");
        Thread thread2 = new Thread(myPriority, "thread2");
        Thread thread3 = new Thread(myPriority, "thread3");
        Thread thread4 = new Thread(myPriority, "thread4");
        Thread thread5 = new Thread(myPriority, "thread5");

        thread1.start();

        thread2.setPriority(1);
        thread2.start();

        thread3.setPriority(4);
        thread3.start();

        thread4.setPriority(Thread.MAX_PRIORITY);
        thread4.start();

        // 超出最大值报错
        thread5.setPriority(11);
        thread5.start();
    }
}

class MyPriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---" + Thread.currentThread().getPriority());
    }
}
