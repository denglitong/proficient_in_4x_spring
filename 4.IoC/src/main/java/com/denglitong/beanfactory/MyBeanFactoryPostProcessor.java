package com.denglitong.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        BeanDefinition bd = factory.getBeanDefinition("myCar");
        bd.getPropertyValues().addPropertyValue("brand", "奇瑞 QQ");
        System.out.println("调用 BeanFactoryPostProcessor.postProcessBeanFactory().");
    }

}
