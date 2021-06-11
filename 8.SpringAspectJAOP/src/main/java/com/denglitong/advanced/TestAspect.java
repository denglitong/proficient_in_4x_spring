package com.denglitong.advanced;

import com.denglitong.fun.Monitorable;
import com.denglitong.fun.Waiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
@Aspect
public class TestAspect {

    @Around("execution(* greetTo(..)) && target(com.denglitong.fun.NaiveWaiter)")
    public Object jointPointAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--- jointPointAccess() ---");

        System.out.println("args[0]: " + joinPoint.getArgs()[0]);
        System.out.println("signature: " + joinPoint.getSignature());
        Object obj = joinPoint.proceed();

        System.out.println("--- jointPointAccess() ---");
        return obj;
    }

    // 绑定连接点方法入参
    @Before("target(com.denglitong.fun.NaiveWaiter) && args(name, num, ..)")
    public void bindJoinPointParams(int num, String name) { // 增强方法接收连接点的参数（入参顺序在 args() 声明，参数类型在此处声明）
        System.out.println("--- bindJoinPointParams() ---");
        System.out.println("name: " + name);
        System.out.println("num: " + num);
        System.out.println("--- bindJoinPointParams() ---");
    }

    // 绑定连接点代理对象，代理对象是代理类类型，即 NaiveWaiter$$EnhancerBySpringCGLIB
    @Before("this(waiter)")
    public void thisBindProxyObj(Waiter waiter) {
        System.out.println("--- thisBindProxyObj() ---");
        System.out.println(waiter.getClass().getName());
        System.out.println("--- thisBindProxyObj() ---");
    }

    // 功能供 thisBindProxyObj，代理对象是目标类类型，即 NaiveWaiter
    @Before("target(waiter)")
    public void targetBindProxyObj(Waiter waiter) {
        System.out.println("--- targetBindProxyObj() ---");
        System.out.println(waiter.getClass().getName());
        System.out.println("--- targetBindProxyObj() ---");
    }

    @Before("@within(monitorable)")
    public void bindTypeAnnotationObj(Monitorable monitorable) {
        System.out.println("--- bindTypeAnnotationObj() ---");
        // com.sun.proxy.$Proxy2
        // 从输出中发现，使用 CGLib 代理时，目标类的注解对象也被代理了
        System.out.println(monitorable.getClass().getName());
        System.out.println("--- bindTypeAnnotationObj() ---");
    }

    @AfterReturning(value = "target(com.denglitong.fun.Seller)", returning = "retVal")
    public void bindReturnValue(int retVal) {
        System.out.println("--- bindReturnValue() ---");
        System.out.println("returnValue: " + retVal);
        System.out.println("--- bindReturnValue() ---");
    }

    @AfterThrowing(value = "target(com.denglitong.fun.SmartSeller)", throwing = "iae")
    public void bindException(IllegalArgumentException iae) {
        System.out.println("--- bindException() ---");
        System.out.println("exception: " + iae.getMessage());
        System.out.println("--- bindException() ---");
    }
}
