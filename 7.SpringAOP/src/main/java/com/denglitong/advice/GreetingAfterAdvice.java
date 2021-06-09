package com.denglitong.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnObj, Method method, Object[] args, Object obj) throws Throwable {
        System.out.println("Please enjoy yourself!");
    }
}
