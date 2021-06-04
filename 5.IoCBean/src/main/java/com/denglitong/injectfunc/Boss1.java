package com.denglitong.injectfunc;

import com.denglitong.simple.Car;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class Boss1 {

    public Car getCar() {
        Car car = new Car();
        car.setBrand("宝马 Z4");
        return car;
    }
}
