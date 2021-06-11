package com.denglitong.aspectj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public class AspectJProxyTest {

    public static void main(String[] args) {
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(new NaiveWaiter());
        factory.addAspect(PreGreetingAspect.class);

        Waiter waiter = factory.getProxy();
        waiter.serveTo("葱头");
        waiter.greetTo("葱头");
        new AnotherWaiter().greetTo("葱头");

        String config = "classpath:com/denglitong/aspectj/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Waiter waiter1 = ctx.getBean("waiter", Waiter.class);
        waiter1.greetTo("葱头");
        waiter1.serveTo("葱头");
        AnotherWaiter anotherWaiter = ctx.getBean("anotherWaiter", AnotherWaiter.class);
        anotherWaiter.greetTo("葱头");

        String config2 = "classpath:com/denglitong/aspectj/beans-schema.xml";
        ApplicationContext ctx2 = new ClassPathXmlApplicationContext(config2);
        Waiter waiter2 = ctx2.getBean("waiter", Waiter.class);
        waiter2.serveTo("葱头");
        waiter2.greetTo("葱头");
    }
}
