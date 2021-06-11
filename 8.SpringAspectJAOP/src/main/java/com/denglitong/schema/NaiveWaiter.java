package com.denglitong.schema;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String clientName) {
        System.out.println("NaiveWaiter:greet to " + clientName + "...");
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NaiveWaiter:serving to " + clientName + "...");
    }

    public void smile(String clientName, int times) {
        System.out.println("NaiveWaiter:smile to " + clientName + " " + times + " times");
    }
}
