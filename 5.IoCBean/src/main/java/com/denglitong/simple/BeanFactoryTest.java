package com.denglitong.simple;

import com.denglitong.annotation.LogonService;
import com.denglitong.annotation.MyComponent;
import com.denglitong.annotation.UserDAO;
import com.denglitong.conf.CacheManager;
import com.denglitong.conf.ServiceConfig;
import com.denglitong.injectfunc.Boss1;
import com.denglitong.injectfunc.MagicBoss;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class BeanFactoryTest {

    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans2.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println(car);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:beans.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        car = factory.getBean("car", Car.class);
        System.out.println(car);

        // can not create abstract bean
        // Property property = factory1.getBean("property", Property.class);
        // System.out.println(property);
        Property childProperty = factory.getBean("childProperty", Property.class);
        System.out.println(childProperty);

        Car car1 = factory.getBean("car1", Car.class);
        System.out.println(car1);

        Car car2 = factory.getBean("car2", Car.class);
        System.out.println(car2);

        Foo foo = factory.getBean("foo", Foo.class);
        Bar bar = factory.getBean("bar", Bar.class);
        System.out.println(foo);
        System.out.println(bar);

        Car car3 = factory.getBean("car3", Car.class);
        System.out.println(car3);

        Car car4 = factory.getBean("car4", Car.class);
        System.out.println(car4);

        List<String> listStr = factory.getBean("listStr", List.class);
        System.out.println(listStr);

        List<Car> listCar = factory.getBean("listCar", List.class);
        System.out.println(listCar);

        Map map = factory.getBean("map", HashMap.class);
        System.out.println(map);

        Car carP = factory.getBean("car_p", Car.class);
        System.out.println(carP);

        Bar barP = factory.getBean("bar_p", Bar.class);
        System.out.println(barP);

        MagicBoss magicBoss = factory.getBean("magicBoss", MagicBoss.class);
        System.out.println(magicBoss.getCar());

        Boss1 boss1Raw = factory.getBean("boss1_raw", Boss1.class);
        System.out.println(boss1Raw.getCar());
        Boss1 boss1 = factory.getBean("boss1", Boss1.class);
        System.out.println(boss1.getCar());

        Car car5 = factory.getBean("car5", Car.class);
        Car car6 = factory.getBean("car6", Car.class);
        System.out.println(car5);
        System.out.println(car6);
        Car car7 = factory.getBean("car7", Car.class);
        Car car8 = factory.getBean("car8", Car.class);
        System.out.println(car7);
        System.out.println(car8);

        CacheManager cacheManager = factory.getBean("cacheManager", CacheManager.class);

        ResourcePatternResolver resolver2 = new PathMatchingResourcePatternResolver();
        Resource resource2 = resolver2.getResource("classpath:beans2.xml");

        DefaultListableBeanFactory factory2 = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader2 = new XmlBeanDefinitionReader(factory2);
        reader2.loadBeanDefinitions(resource2);

        car = factory2.getBean("car", Car.class);
        System.out.println(car);

        Car car10 = factory2.getBean("car10", Car.class);
        System.out.println(car10);

        CarFactoryBean carFactoryBean = factory2.getBean("&car10", CarFactoryBean.class);
        System.out.println(carFactoryBean);

        UserDAO userDAO = factory2.getBean("userDAO", UserDAO.class);
        System.out.println(userDAO);

        // 注入不了 @Autowired 注解的属性，why?
        LogonService logonService = factory2.getBean("logonService", LogonService.class);
        System.out.println(logonService);

        // @Autowired cannot work yet...
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.denglitong.annotation.LogonService");
        logonService = (LogonService)clazz.newInstance();
        System.out.println(logonService);

        // works fine, so ... applicationContext can autowired and beanFactory not?
        logonService = context.getBean("logonService", LogonService.class);
        System.out.println(logonService);

        MyComponent myComponent = context.getBean("myComponent", MyComponent.class);
        System.out.println(myComponent);

        LogonService logonService2 = context.getBean("logonService2", LogonService.class);
        System.out.println(logonService2);

        ServiceConfig serviceConfig = context.getBean(ServiceConfig.class);
        System.out.println(serviceConfig.getDaoConfig());

        UserDAO customUserDAO = context.getBean("customUserDAO", UserDAO.class);
        System.out.println(customUserDAO);
    }
}
