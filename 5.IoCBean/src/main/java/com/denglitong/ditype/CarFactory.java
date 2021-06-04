package com.denglitong.ditype;

import com.denglitong.simple.Car;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class CarFactory {

    // 非静态工厂方法
    public Car createHongQiCar() {
        Car car = new Car();
        car.setBrand("红旗 CA72");
        return car;
    }

    // 静态工厂方法
    public static Car createStaticHongQiCar() {
        Car car = new Car();
        car.setBrand("红旗 CA72");
        car.setMaxSpeed(200);
        return car;
    }
}
