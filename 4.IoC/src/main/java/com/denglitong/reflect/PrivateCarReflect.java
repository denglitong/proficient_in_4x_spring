package com.denglitong.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class PrivateCarReflect {

    public static void main(String[] args) throws Throwable {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.denglitong.reflect.PrivateCar");
        PrivateCar privateCar = (PrivateCar)clazz.newInstance();

        Field colorField = clazz.getDeclaredField("color");
        colorField.setAccessible(true);
        colorField.set(privateCar, "红色");

        Method driveMethod = clazz.getDeclaredMethod("drive", (Class[])null);
        driveMethod.setAccessible(true);
        driveMethod.invoke(privateCar, (Object[])null);
    }

}
