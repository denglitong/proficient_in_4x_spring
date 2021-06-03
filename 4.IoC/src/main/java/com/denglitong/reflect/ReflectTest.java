package com.denglitong.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/2
 */
public class ReflectTest {

    public static Car initByDefaultConst() throws Throwable {
        // 通过类加载器获取 Car 类对象
        // 获取当前线程的 ClassLoader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.denglitong.reflect.Car");

        // 获取类的默认构造器对象并通过它实例化 Car
        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        Car car = (Car)cons.newInstance();

        // 通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗 CA72");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", Integer.class);
        setMaxSpeed.invoke(car, 200);

        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        System.out.println(car);
        car.introduce();
    }
}
