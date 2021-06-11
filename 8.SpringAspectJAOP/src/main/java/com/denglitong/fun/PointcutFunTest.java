package com.denglitong.fun;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public class PointcutFunTest {

    public static void main(String[] args) throws Throwable {
        String configPath = "classpath:com/denglitong/fun/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        // 这里只能使用 Waiter 定义而不能使用 NaiveWaiter，为何？
        NaiveWaiter naiveWaiter = ctx.getBean("naiveWaiter", NaiveWaiter.class);
        NaughtyWaiter naughtyWaiter = ctx.getBean("naughtyWaiter", NaughtyWaiter.class);
        naiveWaiter.greetTo("John");
        naiveWaiter.serveTo("John");
        naughtyWaiter.greetTo("Tom");
        naughtyWaiter.serveTo("Tom");

        for (Class<?> anInterface : naiveWaiter.getClass().getInterfaces()) {
            System.out.println(anInterface.getName());
        }

        Seller seller = (Seller)naiveWaiter;
        seller.sell("Apple", "John");
    }
}
