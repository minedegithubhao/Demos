package org.example.syn;

import java.util.ArrayList;

public class UnSafeList {

    public static void main(String[] args) throws InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (arrayList) {
                    arrayList.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(arrayList.size());
    }
}
