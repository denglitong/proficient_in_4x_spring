package com.denglitong.beanfactory;

import com.denglitong.reflect.Car;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class BeanFactoryTest {

    public static void main(String[] args) throws Throwable {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:beans.xml");
        System.out.println(resource.getURL());

        // 初始化 BeanFactory 时，必须为其提供一种日志框架，这样启动 Spring 容器才不会报错
        // BeanFactory 在初始化容器时并未实例化 Bean，直到第一次访问时才初始化
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        // 这里的 "myCar" 要和 resource 对应的 beans.xml 里面的 bean.id 相同
        Car car = factory.getBean("myCar", Car.class);
        // car = factory.getBean(Car.class);
        System.out.println("car bean is ready for use!");
        car.introduce();

        // 相对于类路径 classpath （src/main/resources）
        // ApplicationContext 初始化时将实例化所有单实例的 Bean
        ApplicationContext ctx1 = new ClassPathXmlApplicationContext("beans.xml");
        Car car1 = ctx1.getBean("myCar", Car.class);
        car1.introduce();

        // 相对于项目根路径
        String fileName = "/4.IoC/src/main/resources/beans.xml";
        ApplicationContext ctx2 = new FileSystemXmlApplicationContext(fileName);
        Car car2 = ctx2.getBean("myCar", Car.class);
        car2.introduce();

        /**
         * Spring 支持基于类注解的配置方式，主要功能来自 Spring 的一个名为 JavaConfig 的子项目，
         * 项目已经是 Spring 核心框架的一部分。一个 @Configuration 注解的 POJO 即可提供 Spring
         * 所需的 Bean 配置信息。
         */
    }

}
