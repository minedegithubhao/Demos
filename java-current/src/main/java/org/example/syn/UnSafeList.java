package org.example.syn;

import java.util.ArrayList;

public class UnSafeList {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> arrayList.add(Thread.currentThread().getName())).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(arrayList.size());
    }
}
