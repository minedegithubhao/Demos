package org.example.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class myLog implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("在执行" + method.getName() + "方法之前");
    }
}
