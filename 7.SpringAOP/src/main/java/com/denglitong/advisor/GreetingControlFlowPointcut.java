package com.denglitong.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.ControlFlowPointcut;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class GreetingControlFlowPointcut extends ControlFlowPointcut {

    public GreetingControlFlowPointcut(Class<?> clazz) {
        super(clazz);
    }

    public GreetingControlFlowPointcut(Class<?> clazz, String methodName) {
        super(clazz, methodName);
    }

    // 开启之后，在 ControlFlowPointcut 切点的基础上，还需要满足 classFilter 才生效
    /*@Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return "Waiter".equals(clazz.getName());
            }
        };
    }*/
}
