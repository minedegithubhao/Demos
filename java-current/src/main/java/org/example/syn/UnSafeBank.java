package org.example.syn;

import lombok.SneakyThrows;

public class UnSafeBank {

    public static void main(String[] args) {

        Account ali = new Account("支付宝", 100);

        Drawing nan = new Drawing(ali, 60, "nan");
        Drawing nv = new Drawing(ali, 80, "nv");

        nan.start();
        nv.start();
    }
}

class Account {

    public String name;

    public int amount;

    public Account(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}

class Drawing extends Thread {
    private Account account;
    private int drawingMoney;
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @SneakyThrows
    @Override
    public void run() {

        //synchronized锁共享资源才行
        synchronized (account){
            Thread.sleep(100);
            if (account.amount - drawingMoney < 0){
                System.out.println(Thread.currentThread().getName() + "钱不够， 取不了");
                return;
            }

            // 卡内余额
            account.amount = account.amount - drawingMoney;

            nowMoney = nowMoney = drawingMoney;

            System.out.println(account.name + "余额为：" + account.amount);
            System.out.println(this.getName() + "手里的钱为：" + nowMoney);
        }
    }
}
