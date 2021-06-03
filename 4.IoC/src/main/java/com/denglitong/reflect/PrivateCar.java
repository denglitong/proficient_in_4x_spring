package com.denglitong.reflect;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class PrivateCar {

    private String color;

    protected void drive() {
        System.out.println("drive private car! the color is: " + color);
    }
}
