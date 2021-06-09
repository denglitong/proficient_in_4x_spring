package com.denglitong.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class GreetingInterceptor implements MethodInterceptor {

    /**
     * 截获目标方法的执行，并在前后添加横切逻辑
     * 环绕增强达到了 前置增强+后置增强 的联合效果
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 前置织入
        Object[] args = invocation.getArguments();
        String clientName = (String)args[0];
        System.out.println("How are you! Mr." + clientName + ".");

        // 通过反射机制调用目标方法
        Object result = invocation.proceed();

        // 后置织入
        System.out.println("Please enjoy yourself!");

        // 返回目标函数执行结果
        return result;
    }
}
