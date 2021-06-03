package com.denglitong.reflect;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * 实现 Bean 生命周期管理接口的 Car
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/2
 */
public class Car implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean {

    private String brand;

    private String color;

    private Integer maxSpeed;

    private BeanFactory beanFactory;

    private String beanName;

    public Car() {
        System.out.println("调用 Car() 构造函数。");
    }

    public Car(String brand, String color, Integer maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用 BeanFactoryAware.setBeanFactory().");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("调用 BeanNameAware.setBeanName().");
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用 InitializingBean.afterPropertiesSet().");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用 DisposableBean.destroy().");
    }

    // 通过 <bean> 的 init-method 属性指定的初始化方法
    public void myInit() {
        System.out.println("调用 init-method 所指定的 myInit()，将 maxSpeed 设置为 240.");
        this.maxSpeed = 240;
    }

    // 通过 <bean> 的 destroy-method 属性指定的销毁方法
    public void myDestroy() {
        System.out.println("调用 destroy-method 所指定的 myDestroy().");
    }

    public void introduce() {
        System.out.println("brand:" + brand + ";color:" + color + ";maxSpeed:" + maxSpeed);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.setBrand("红旗 CA72");
        System.out.println(car);
        car = new Car("红旗 CA72", "黑色", 240);
        System.out.println(car);
    }
}
