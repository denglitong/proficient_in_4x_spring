package com.denglitong.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        // 切点的方法匹配规则：方法名为 greetTo
        return "greetTo".equals(method.getName());
    }

    // 默认情况下匹配所有的类，这里覆盖 getClasssFilter 方法
    @Override
    public ClassFilter getClassFilter() {
        // 切点的类匹配规则：Waiter 的类或子类
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return Waiter.class.isAssignableFrom(clazz);
            }
        };
    }
}
