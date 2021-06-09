package com.denglitong.autoproxy;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
@Component
public class BeanSelfProxyAwareMounter implements SystemBootAddon, ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(BeanSelfProxyAwareMounter.class);
    private ApplicationContext applicationContext;

    // 注入 Spring 容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onReady() {
        Map<String, BeanSelfProxyAware> proxyAwareMap = applicationContext.getBeansOfType(BeanSelfProxyAware.class);
        if (proxyAwareMap != null) {
            for (BeanSelfProxyAware beanSelfProxyAware : proxyAwareMap.values()) {
                // 将自动代理类注入自身
                beanSelfProxyAware.setSelfProxy(beanSelfProxyAware);
                if (logger.isDebugEnabled()) {
                    logger.debug("注册自身被代理的实例。");
                }
            }
        }
    }

    @Override
    public int getOrder() {
        // 值越大越最后被加载
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
