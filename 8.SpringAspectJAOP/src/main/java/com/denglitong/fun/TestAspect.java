package com.denglitong.fun;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
@Aspect
public class TestAspect implements Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    // 后置切面增强
    @AfterReturning("@annotation(com.denglitong.anno.NeedTest)")
    public void needTestFun() {
        System.out.println("-- needTestFun() executed! --");
    }

    @AfterReturning("within(com.denglitong.fun.*) && execution(* greetTo(..))")
    public void greetToFun() {
        System.out.println("-- greetToFun() executed! --");
    }

    @AfterReturning("!target(com.denglitong.fun.NaiveWaiter) && execution(* serveTo(..))")
    public void notServeInNaiveWaiter() {
        System.out.println("-- notServeInNaiveWaiter() executed! --");
    }

    @AfterReturning("target(com.denglitong.fun.Waiter) || " +
            " target(com.denglitong.fun.Seller)")
    public void waiterOrSeller() {
        System.out.println("-- waiterOrSeller() executed! --");
    }

    // Waiter.greetTo() 匹配了waiterOrSeller这个织点，但却没匹配上thisTest()织点，why？
    @AfterReturning("this(com.denglitong.fun.Seller)")
    public void thisTest() {
        System.out.println("-- thisTest() executed! --");
    }

    @AfterReturning("TestNamePointcut.inPackageGreetTo()")
    public void packageGreetTo() {
        System.out.println("-- packageGreetTo() executed! --");
    }

    @AfterReturning("!target(com.denglitong.fun.NaiveWaiter) && " +
            " TestNamePointcut.inPackageGreetTo()")
    public void packageGreetToNotNaiveWaiter() {
        System.out.println("-- packageGreetToNotNaiveWaiter() executed! --");
    }
}
