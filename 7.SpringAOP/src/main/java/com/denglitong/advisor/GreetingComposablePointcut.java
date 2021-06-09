package com.denglitong.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.*;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class GreetingComposablePointcut {

    public Pointcut getIntersectionPointcut() {
        ComposablePointcut cp = new ComposablePointcut();

        Pointcut p1 = new ControlFlowPointcut(WaiterDelegate.class, "service");
        NameMatchMethodPointcut p2 = new NameMatchMethodPointcut();
        p2.addMethodName("greetTo");

        // return cp.intersection(p1).intersection((MethodMatcher)p2);
        return Pointcuts.intersection(p1, p2);
    }
}
