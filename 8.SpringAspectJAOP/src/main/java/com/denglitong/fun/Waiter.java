package com.denglitong.fun;

import com.denglitong.anno.NeedTest;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public interface Waiter {
    @NeedTest
    void greetTo(String clientName);
    void serveTo(String clientName);
}
