package com.denglitong.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
public abstract class BaseDomain implements Serializable {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
