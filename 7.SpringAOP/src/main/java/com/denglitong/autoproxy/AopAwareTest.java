package com.denglitong.autoproxy;

import com.denglitong.advisor.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public class AopAwareTest {

    public static void main(String[] args) {
        String configPath = "classpath:com/denglitong/autoproxy/beans-aware.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = ctx.getBean("waiter", Waiter.class);
        waiter.serveTo("John");
        // waiter.greetTo("John");

        com.denglitong.autoproxy.Waiter waiter1 = ctx.getBean("waiterSelfProxy",
                com.denglitong.autoproxy.Waiter.class);
        waiter1.serveTo("Tom");
    }
}
