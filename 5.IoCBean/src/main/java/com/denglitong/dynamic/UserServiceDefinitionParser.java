package com.denglitong.dynamic;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class UserServiceDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        // 创建 Bean 定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserService.class);

        // 获取自定义标签的属性
        String dao = element.getAttribute("dao");
        beanDefinitionBuilder.addPropertyReference("userDAO", dao);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 注册 Bean 定义
        parserContext.registerBeanComponent(new BeanComponentDefinition(beanDefinition, "userService1"));

        // 现在，可以将 UserServiceDefinitionParser 解析器注册到 Spring 命名空间解析器了
        // 在这里 parserContext 注册了一个 name 为 userService 的 UserService <bean>

        return null;
    }
}
