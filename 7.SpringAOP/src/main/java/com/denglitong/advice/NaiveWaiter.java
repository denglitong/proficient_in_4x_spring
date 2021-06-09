package com.denglitong.advice;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving to " + name + "...");
    }
}
