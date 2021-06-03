package com.denglitong.beanfactory;

import com.denglitong.reflect.Car;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class BeanLifeCycle {

    private static final String BEAN_CAR = "myCar";

    private static void LifeCycleInBeanFactory() {
        // 装配文件并启动容器
        Resource resource = new ClassPathResource("beans2.xml");

        BeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)factory);
        reader.loadBeanDefinitions(resource);

        // 向容器中注册 MyBeanPostProcessor 后处理器
        ((ConfigurableBeanFactory)factory).addBeanPostProcessor(new MyBeanPostProcessor());
        // 向容器中注册 MyInstantiationAwareBeanPostProcessor 后处理器
        ((ConfigurableBeanFactory)factory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 后处理器的调用顺序和注册顺序无关，多个后处理器须通过实现 Ordered 接口来确定调用顺序

        // 第一次从容器中获取 car，将触发容器实例化 Bean，这将引发 Bean 生命周期方法的调用
        Car car1 = (Car)factory.getBean(BEAN_CAR);
        car1.introduce();
        car1.setColor("红色");

        // 第二次从容器中获取 car，直接从缓存池中获取
        Car car2 = (Car)factory.getBean(BEAN_CAR);
        System.out.println("car1 == car2 : " + (car1 == car2));

        // 关闭容器
        ((ConfigurableBeanFactory)factory).destroySingletons();
    }

    public static void main(String[] args) {
        LifeCycleInBeanFactory();
    }
}
