package com.denglitong.advisor;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class Waiter {

    public void greetTo(String name) {
        System.out.println("waiter greet to " + name + "...");
    }

    // 当方法从类的外部调用时，会使用增强的代理类
    public void serveTo(String name) {
        System.out.println("waiter serving " + name + "...");
        // 当方法在类内部调用时，不会使用增强的代理类，而是直接使用未被增强类的方法
        greetTo(name);
    }
}
