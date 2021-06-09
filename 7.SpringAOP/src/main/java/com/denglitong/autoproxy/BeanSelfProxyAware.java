package com.denglitong.autoproxy;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public interface BeanSelfProxyAware {
    // 织入自身代理类接口
    void setSelfProxy(Object object);
}
