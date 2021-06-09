package com.denglitong.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {

    private static List<String> specialClientList = new ArrayList<>();

    static {
        specialClientList.add("John");
        specialClientList.add("Tom");
    }

    public GreetingDynamicPointcut() {
        super();
    }

    // 对类进行静态切点检查
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                System.out.println("调用 getClassFilter() 对 " + clazz.getName() +
                        " 做静态检查.");
                return Waiter.class.isAssignableFrom(clazz);
            }
        };
    }

    // 对方法名做静态切点检查
    @Override
    public boolean matches(Method method, Class<?> clazz) {
        System.out.println("调用 matches(method, clazz) " + clazz.getName() + "." +
                method.getName() + " 做静态检查.");
        return "greetTo".equals(method.getName());
    }

    // 对方法进行动态切点检查
    // 动态检查会对性能造成很大的影响，经尽量避免
    @Override
    public boolean matches(Method method, Class<?> clazz, Object... args) {
        System.out.println("调用 matches(method, clazz, args)" + clazz.getName() +
                "." + method.getName() + " 做动态检查.");
        // 判断入参是否符合动态检查的范围
        String clientName = (String)args[0];
        return specialClientList.contains(clientName);
    }
}
