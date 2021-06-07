package com.denglitong.editor;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class Boss {

    private String name;

    private Car car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}
