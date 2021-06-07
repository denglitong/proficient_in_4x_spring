package com.denglitong.beanproperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class ApplicationManager {

    private Integer sessionTimeout;

    // 在基于注解和基于 Java 类配置的 Bean 中，
    // 可通过 @Value("#{beanName.propertyName}) 的注解形式引用 Bean 的属性值。
    @Value("#{systemConfig.maxTabPageNum}")
    private Integer maxTabPageNum;

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Integer getMaxTabPageNum() {
        return maxTabPageNum;
    }

    public void setMaxTabPageNum(Integer maxTabPageNum) {
        this.maxTabPageNum = maxTabPageNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
