package com.denglitong.autoproxy;

import org.springframework.core.Ordered;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public interface SystemBootAddon extends Ordered {
    // 在系统就绪后调用的方法
    void onReady();
}
