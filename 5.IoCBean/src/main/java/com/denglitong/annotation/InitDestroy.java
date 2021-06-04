package com.denglitong.annotation;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Component
public class InitDestroy {

    public InitDestroy() {
        System.out.println("Create InitDestroy");
    }

    /**
     * 在使用 <bean> 进行配置时可通过 init-method 和 destroy-method 属性
     * 指定 Bean 的初始化及容器销毁前执行的方法；Spring 从2.5开始支持
     * <p>
     * \@PostConstruct 和 @PreDestroy 注解，效果和上述相同，不过可以注解在多个方法上
     */
    @PostConstruct
    private void init1() {
        System.out.println("execute in init1");
    }

    @PostConstruct
    private void init2() {
        System.out.println("execute in init2");
    }

    @PreDestroy
    private void destroy1() {
        System.out.println("execute in destroy1");
    }

    @PreDestroy
    private void destroy2() {
        System.out.println("execute in destroy2");
    }

}
