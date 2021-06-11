package com.denglitong.aspectj;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("NaiveWaiter:greetTo " + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("NaiveWaiter:serving " + name + "...");
    }
}
