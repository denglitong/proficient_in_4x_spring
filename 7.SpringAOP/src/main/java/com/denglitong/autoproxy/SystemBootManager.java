package com.denglitong.autoproxy;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
@Component
public class SystemBootManager implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(SystemBootManager.class);
    private List<SystemBootAddon> systemBootAddons = Collections.EMPTY_LIST;
    private boolean hasRunOnce = false;

    // 注入所有 SystemAddon 插件
    @Autowired
    public void setSystemBootAddons(List<SystemBootAddon> systemBootAddons) {
        this.systemBootAddons = systemBootAddons;
    }

    // 触发所有插件
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!hasRunOnce) {
            for (SystemBootAddon systemBootAddon : systemBootAddons) {
                systemBootAddon.onReady();
                if (logger.isDebugEnabled()) {
                    logger.debug("执行插件：{}", systemBootAddon.getClass().getCanonicalName());
                }
            }
            hasRunOnce = true;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("已执行过容器启动插件集，本次忽略。");
            }
        }
    }
}
