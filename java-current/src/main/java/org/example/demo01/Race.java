package org.example.demo01;

/**
 * 模拟龟兔赛跑
 */
public class Race implements Runnable{
    private String winner = null;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            //判断比赛是否结束了
            if (gameOver(i)){
                break;
            }

            if (Thread.currentThread().getName().equals("兔子") && i%10 == 1){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName() + "--> 跑了" + i + "步");
        }
    }
    private boolean gameOver(int steps){
        //判断是否有胜利者
        if (winner != null){
            return true;
        }
        if (steps >= 100){
            winner = Thread.currentThread().getName();
            System.out.println("winner is: " + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
