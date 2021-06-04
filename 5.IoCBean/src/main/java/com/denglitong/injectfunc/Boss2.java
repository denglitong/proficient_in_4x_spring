package com.denglitong.injectfunc;

import com.denglitong.simple.Car;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * 用于替换他人的 Bean 必需实现 MethodReplacer 接口
 * Spring 将利用该方法替换目标 bean 的方法
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class Boss2 implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        Car car = new Car();
        car.setBrand("美人豹");
        return car;
    }
}
