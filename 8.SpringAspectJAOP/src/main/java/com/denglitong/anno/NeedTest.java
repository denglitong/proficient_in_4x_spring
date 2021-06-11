package com.denglitong.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 没有成员的注解称为标识注解
 * 注解不允许显式继承其他接口
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedTest {
    // 成员以无入参、无抛出异常的方式声明
    // 成员类型是受限的，基本类型及其包装类型、String、Class、enums、注解类型及其上述类型的数组
    // 如果注解只有一个成员，则成员名必须为 value()，在使用时可忽略成员名和赋值号
    //
    boolean value() default false;
}
