package com.denglitong.beanfactory;

import com.denglitong.reflect.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.StringUtils;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    // resources/beans.xml#bean.id
    private static final String BEAN_CAR = "myCar";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_CAR.equals(beanName)) {
            Car car = (Car)bean;
            if (car.getColor() == null) {
                System.out.println("调用 BeanPostProcessor.postProcessBeforeInitialization()，color 为空，设置为默认黑色。");
                car.setColor("黑色");
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_CAR.equals(beanName)) {
            Car car = (Car)bean;
            if (car.getMaxSpeed() >= 200) {
                System.out.println("调用 BeanPostProcessor.postProcessAfterInitialization()，将 maxSpeed 设置为 200。");
                car.setMaxSpeed(200);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
