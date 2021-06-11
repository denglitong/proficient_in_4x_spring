package com.denglitong.fun;

import com.denglitong.anno.NeedTest;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public class NaughtyWaiter implements Waiter {

    @NeedTest
    @Override
    public void greetTo(String clientName) {
        System.out.println("NaughtyWaiter:greet to " + clientName + "...");
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NaughtyWaiter:serving to " + clientName + "...");
    }

    public void joke(String clientName, int times) {
        System.out.println("NaughtyWaiter:play " + times + " jokes to " + clientName + "...");
    }
}
