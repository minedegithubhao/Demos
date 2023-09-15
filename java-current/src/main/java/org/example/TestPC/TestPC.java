package org.example.TestPC;

/**
 * 生产者消费者模型，利用缓冲取解决：管程法
 * 生产者、消费者、产品、缓冲区
 */
public class TestPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Productor(synContainer).start();
        new Consumer(synContainer).start();
    }
}

class Productor extends Thread {
    private SynContainer synContainer;

    Productor(SynContainer synContainer){
        this.synContainer = synContainer;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 100; i++) {
            synContainer.push(new Chicken(i));
            System.out.println("生产了第" + i + "只鸡");
        }
    }
}

class Consumer extends Thread {
    private SynContainer synContainer;

    Consumer(SynContainer synContainer){
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("消费了第" + synContainer.pop().id + "只鸡");
        }
    }
}

//产品
class Chicken {
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer {
    //需要一个容器
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    //生产者放入产品
    public synchronized void push(Chicken chicken) {
        //如果容器满了，灸需要等待消费者消费
        if (count == chickens.length){
            //等待消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        chickens[count] = chicken;
        count++;

        //可以通知消费者消费了
        this.notifyAll();
    }

    //消费者消费产品
    public synchronized Chicken pop(){
        //如果没有产品了
        if (count == 0){
            //等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //如果没有满就直接消费
        count--;
        Chicken chichen = chickens[count];
        //通知生产者生产
        this.notifyAll();
        return chichen;
    }

}
