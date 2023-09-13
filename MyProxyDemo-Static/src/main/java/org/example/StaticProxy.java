package org.example;

/**
 * 静态代理
 */
public class StaticProxy{

    public static void main(String[] args) {
        You you = new You();
        WeddingCompany weddingCompany = new WeddingCompany(you);
        weddingCompany.HappyMarry();
    }
}
interface Marry {

    void HappyMarry();
}

/**
 * 真实角色
 */
class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("我要结婚了");
    }
}

class WeddingCompany implements Marry{

    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚结束");
    }

    private void before() {
        System.out.println("布置现场");
    }

}