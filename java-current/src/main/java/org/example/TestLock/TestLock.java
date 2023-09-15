package org.example.TestLock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public static void main(String[] args) {
        TestLocak2 testLocak2 = new TestLocak2();
        new Thread(testLocak2).start();
        new Thread(testLocak2).start();
        new Thread(testLocak2).start();
    }
}

class TestLocak2 implements Runnable {

    int tickNum = 10;
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (tickNum > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(tickNum--);
                } else {
                    break;
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
