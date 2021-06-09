package com.denglitong.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * AOP 编织器
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/7
 */
public class PerformanceHandler implements InvocationHandler {

    private Object target;

    /**
     * @param target 目标业务类
     */
    public PerformanceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(proxy.getClass().getName() + "." + method.getName());
        // 通过反射调用业务类的目标方法，proxy 是 target 的封装类，一般不会用到
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
