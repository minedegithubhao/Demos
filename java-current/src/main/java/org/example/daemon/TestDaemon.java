package org.example.daemon;

public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        //守护线程
        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();

        //用户线程
        new Thread(you).start();

    }
}

class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("上帝保佑着你");
        }
    }
}

class You implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("活了" + i + "天");
        }
        System.out.println("===goodbye world!");
    }
}
