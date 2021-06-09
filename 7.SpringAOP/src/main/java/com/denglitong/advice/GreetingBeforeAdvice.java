package com.denglitong.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    /**
     * 方法前置增强
     *
     * @param method 目标类方法
     * @param args   目标类方法的入参
     * @param obj    目标类实例
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object obj) throws Throwable {
        String clientName = (String)args[0];
        System.out.println("How are you! Mr." + clientName + ".");
    }
}
