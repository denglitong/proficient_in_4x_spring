package com.denglitong.fun;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public class TestNamePointcut {

    // 通过函数名对该切点进行命名，private 表示该切点只能在当前切面类中使用
    @Pointcut("within(com.denglitong.fun.*)")
    private void inPackage() {}

    // protected 表示该命名切点可在当前切面类及子切面类中使用
    @Pointcut("execution(* greetTo(..))")
    protected void greetTo() {}

    // 引用切点定义的切点
    // 命名切点仅利用方法名及访问修饰符的信息，所有习惯上方法返回类型为 void, 并且方法体为空
    // 切点定义好后就可以在切面定义时通过名称引用切点
    @Pointcut("inPackage() && greetTo()")
    public void inPackageGreetTo() {}
}
