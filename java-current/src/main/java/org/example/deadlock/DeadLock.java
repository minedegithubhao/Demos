package org.example.deadlock;

public class DeadLock {

    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "灰姑娘");
        Makeup g2 = new Makeup(1, "白雪公主");
        g1.start();
        g2.start();
    }
}

class Lipstick{

}

class Mirror{

}

class Makeup extends Thread{
    //需要的资源只有一份，用static来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;

    String girlName;

    Makeup(int choice, String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeup() throws InterruptedException {
        if (choice==0){
            //获得口红的锁
            synchronized (lipstick){
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);
                // 一秒钟后想获得镜子
//                synchronized (mirror){
//                    System.out.println(this.girlName + "获得镜子的锁");
//                }
            }
            //拿出来灸不会造成死锁了
            synchronized (mirror){
                System.out.println(this.girlName + "获得镜子的锁");
            }
        } else {
            //获得镜子的锁
            synchronized (mirror){
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
                // 一秒钟后想获得镜子
//                synchronized (lipstick){
//                    System.out.println(this.girlName + "获得口红的锁");
//                }
            }
            //拿出来灸不会造成死锁了
            synchronized (lipstick){
                System.out.println(this.girlName + "获得口红的锁");
            }
        }
    }
}