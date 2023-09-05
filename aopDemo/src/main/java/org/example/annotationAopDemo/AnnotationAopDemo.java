package org.example.annotationAopDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationAopDemo {

    @Before("execution(* org.example.service.impl.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("==before==");
    }

    @After("execution(* org.example.service.impl.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("==after==");
    }

    @Around("execution(* org.example.service.impl.UserServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("==环绕==");
        Object proceed = joinPoint.proceed();
        System.out.println("==环绕==");
        return proceed;
    }
}
