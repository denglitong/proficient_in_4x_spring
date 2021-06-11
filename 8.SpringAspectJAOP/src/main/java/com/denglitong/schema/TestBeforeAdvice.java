package com.denglitong.schema;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class TestBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object obj) throws Throwable {
        System.out.println("-- TestBeforeAdvice --");
        System.out.println("clientName: " + args[0]);
        System.out.println("-- TestBeforeAdvice --");
    }
}
