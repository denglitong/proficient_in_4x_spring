package com.denglitong.fun;

import com.denglitong.aspectj.NaiveWaiter;
import com.denglitong.aspectj.Waiter;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public class WaiterManager {

    public void addWaiter(Waiter waiter) {
        System.out.println("add Waiter...");
    }

    public void addNaiveWaiter(NaiveWaiter naiveWaiter) {
        System.out.println("add NaiveWaiter...");
    }
}
