package com.denglitong.dynamic;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class UserServiceNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        // 绑定自定义 Bean 解析器
        registerBeanDefinitionParser("user-service", new UserServiceDefinitionParser());
        // 最后，你需要告诉 Spring 如何解析自定义标签
    }
}
