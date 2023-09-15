package org.example.syn;

import lombok.SneakyThrows;

import java.util.TreeMap;

public class UnSafeBuyTicket {

    public static void main(String[] args) {
        BuyTick buyTick = new BuyTick();
        new Thread(buyTick, "张三").start();
        new Thread(buyTick, "李四").start();
        new Thread(buyTick, "王五").start();
    }
}

class BuyTick implements Runnable {
    private int ticketNum = 10;
    private boolean flag = true;

    @SneakyThrows
    @Override
    public void run() {
        while (flag){
            buy();
            Thread.sleep(100);
        }
    }

    //synchronized 同步方法，锁的是this
    private synchronized void buy() {
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread().getName() + "拿到了" + ticketNum--);
    }
}
