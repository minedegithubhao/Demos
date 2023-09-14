package org.example.status;

/**
 * 观察测试线程的状态
 */
public class TestState {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("。。。。。。。");
            }
        });

        //观察状态 NEW
        System.out.println(thread.getState());

        //启动后的状态RUN
        thread.start();
        System.out.println(thread.getState());

        Thread.State state = thread.getState();
        while (state != Thread.State.TERMINATED){
            Thread.sleep(1000);
            state = thread.getState();
            System.out.println(state);
        }

        // 进入死亡状态，就不能再次启动
        thread.start();
    }
}
