package com.denglitong.beanfactory;

import com.denglitong.reflect.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class GroovyApplicationContextTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new GenericGroovyApplicationContext("classpath:groovy-beans.groovy");
        Car blueCar = ctx.getBean("blueCar", Car.class);
        blueCar.introduce();
    }

}
