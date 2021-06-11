package com.denglitong.fun;

import com.denglitong.anno.NeedTest;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
@Monitorable
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String clientName) {
        System.out.println("NaiveWaiter:greet to " + clientName + "...");
    }

    @NeedTest
    @Override
    public void serveTo(String clientName) {
        System.out.println("NaiveWaiter:serving to " + clientName + "...");
    }

    public void smile(String clientName, int times) {
        System.out.println("NaiveWaiter:smile to " + clientName + " " + times + " times");
    }
}
