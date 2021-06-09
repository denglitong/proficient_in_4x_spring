package com.denglitong.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class TransactionThrowsAdvice implements ThrowsAdvice {

    /**
     * ThrowsAdvice 接口没有定义任何方法，它是一个标签接口，在运行期由 Spring 使用反射机制自行判断
     * <p>
     * 必须采取以下签名形式定义的方法来定义异常抛出的增强方法：
     * void afterThrowing(Method method, Object[] args, Object target, Throwable ex);
     * 方法名必须为 afterThrowing，前三个参数 method/args/target 是可选的（但需要同时出现）
     * <p>
     * 以下都是合法的定义：
     * afterThrowing(SQLException e);
     * afterThrowing(RuntimeException e);
     * afterThrowing(Method method, Object[] args, Object target, Exception e);
     * <p>
     * 目标方法抛出异常时，Spring 会自动选用最匹配的增强方法
     *
     * @param method
     * @param args
     * @param target
     * @param e
     * @throws Throwable
     */
    public void afterThrowing(Method method, Object[] args, Object target,
                              Exception e) throws Throwable {
        System.out.println("-------------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常：" + e.getMessage());
        System.out.println("成功回滚事务。");
    }
}
