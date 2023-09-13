package org.example;

public class TestLambda2 {

    public static void main(String[] args) {
        ILove iLove = new ILove() {
            @Override
            public void love(int a) {
                System.out.println("i love ：" + a);
            }
        };

        iLove.love(1);

        iLove = (int a) ->{
            System.out.println("i love2 :" + a);
        };
        iLove.love(2);

        //1、简化1去掉参数类型
        iLove = (a) -> {
            System.out.println("i love2 :" + a);
        };
        iLove.love(3);

        //2、简化2去掉括号
        iLove = a -> {
            System.out.println("i love2 :" + a);
        };
        iLove.love(4);

        //3、简化3去掉花括号，只能有一行代码
        iLove = a -> System.out.println("i love2 :" + a);
        iLove.love(5);
    }
}

interface ILove{
    void love(int a);
}
