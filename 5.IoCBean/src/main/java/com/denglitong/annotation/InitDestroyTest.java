package com.denglitong.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class InitDestroyTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans2.xml");
        // Spring 先调用构造函数，然后调用 @PostConstruct，最后在容器关闭时执行 @PreDestroy
        InitDestroy destroy = context.getBean(InitDestroy.class);
        ((ClassPathXmlApplicationContext)context).destroy();
    }
}
