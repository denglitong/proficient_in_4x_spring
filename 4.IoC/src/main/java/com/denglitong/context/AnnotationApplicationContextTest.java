package com.denglitong.context;

import com.denglitong.reflect.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class AnnotationApplicationContextTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
        Car car = ctx.getBean("redCar", Car.class);
        car.introduce();
    }

}
