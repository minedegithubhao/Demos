package org.example;

import org.example.service.IUserService;
import org.example.service.impl.UserServiceImpl;

public class Test {

    public static void main(String[] args) {

        // 被代理的对象
        IUserService userService = new UserServiceImpl();
        MyProxyInvocationHandler handler = new MyProxyInvocationHandler();
        handler.setTarget(userService);

        IUserService proxy = (IUserService) handler.getProxy();
        proxy.add();

    }
}
