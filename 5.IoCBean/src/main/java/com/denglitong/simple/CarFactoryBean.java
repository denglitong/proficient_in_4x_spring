package com.denglitong.simple;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String attrs;

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] attrs = this.attrs.split(",");
        car.setBrand(attrs[0]);
        car.setColor(attrs[1]);
        car.setMaxSpeed(Integer.parseInt(attrs[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
