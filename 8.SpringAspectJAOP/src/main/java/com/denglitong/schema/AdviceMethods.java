package com.denglitong.schema;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class AdviceMethods {

    public void preGreeting() {
        System.out.println("-- how are you! --");
    }

    public void postGreeting() {
        System.out.println("-- please enjoy yourself! --");
    }

    // 后置增强，绑定返回值时 returning 属性必须和增强方法的入参名一致
    public void afterReturning(int retVal) {
        System.out.println("-- afterReturning --");
        System.out.println(retVal);
        System.out.println("-- afterReturning --");
    }

    // 环绕增强，ProceedingJoinPoint可访问到环绕增强的连接点信息
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-- aroundMethod begin --");
        Object obj = joinPoint.proceed();
        System.out.println("-- aroundMethod end --");
        return obj;
    }

    public void afterThrowing(IllegalArgumentException iae) {
        System.out.println("-- afterThrowing --");
        System.out.println(iae.getMessage());
        System.out.println("-- afterThrowing --");
    }

    public void afterMethod() {
        System.out.println("-- afterMethod --");
    }

    public void bindParams(String name, int num) {
        System.out.println("-- bindParams --");
        System.out.println("name: " + name);
        System.out.println("num: " + num);
        System.out.println("-- bindParams --");
    }
}
