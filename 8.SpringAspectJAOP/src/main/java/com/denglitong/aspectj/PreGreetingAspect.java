package com.denglitong.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 通过注解和代码，将切点、增强类型和增强的横切逻辑糅合到一个类中，使切面的定义浑然天成
 * <p>
 * 虽然可以通过编程的方式注入切面，但在一般情况下，都是通过 Spring 的配置完成切面织入工作
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
@Aspect // 通过该注解将 POJO 标识为一个切面
public class PreGreetingAspect {

    // @Before 表示前置增强，成员值是一个 @AspectJ 切点表达式：
    // 它的意思是：在目标类的 greetTo() 方法上织入增强，greetTo() 方法可以是带任意入参和返回值
    @Before(value = "execution(* greetTo(..))") // 定义切点和增强类型
    public void beforeGreeting() {
        // 增强的横切逻辑
        System.out.println("How are you");
    }
}
