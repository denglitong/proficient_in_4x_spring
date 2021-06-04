package com.denglitong.simple;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class Car {

    private String brand;

    private String color;

    private Integer maxSpeed;

    // 装载 bean 需要有默认的无参构造器，否则属性注入会抛异常
    // 所以如果声明了带参构造器，还需显式提供无参构造器
    // （JVM只会在类没有声明任何构造器时才会自动注入一个无参构造器）
    public Car() {
    }

    public Car(String brand, String color, Integer maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    // getter and setter

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

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
