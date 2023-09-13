package org.example.status;

public class TestSleep2 {

    private static void tenDown() throws InterruptedException {
        int i = 10;
        while (true){
            Thread.sleep(1000);
            System.out.println(i--);
            if (i < 0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            tenDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
