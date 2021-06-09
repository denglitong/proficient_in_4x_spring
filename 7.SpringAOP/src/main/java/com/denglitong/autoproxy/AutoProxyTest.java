package com.denglitong.autoproxy;

import com.denglitong.advisor.Manager;
import com.denglitong.advisor.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public class AutoProxyTest {

    public static void main(String[] args) {
        String configs2 = "classpath:com/denglitong/autoproxy/beans2.xml";
        ApplicationContext ctx2 = new ClassPathXmlApplicationContext(configs2);
        Waiter waiter7 = ctx2.getBean("waiter", Waiter.class);
        waiter7.greetTo("John");
        waiter7.serveTo("Tom");
        Manager manager = ctx2.getBean("manager", Manager.class);
        manager.greetTo("John");

        String configs3 = "classpath:com/denglitong/autoproxy/beans3.xml";
        ApplicationContext ctx3 = new ClassPathXmlApplicationContext(configs3);
        Waiter waiter8 = ctx3.getBean("waiter", Waiter.class);
        waiter8.greetTo("John");
        waiter8.serveTo("Tom");
        Manager manager1 = ctx3.getBean("manager", Manager.class);
        manager1.greetTo("John");
    }
}
