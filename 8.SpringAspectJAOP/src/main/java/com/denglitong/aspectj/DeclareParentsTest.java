package com.denglitong.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public class DeclareParentsTest {

    public static void main(String[] args) {
        String config = "classpath:com/denglitong/aspectj/beans-schema.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Waiter waiter = (Waiter)ctx.getBean("waiter");
        waiter.greetTo("John");
        Seller seller = (Seller)waiter;
        seller.sell("Beer", "John");
    }
}
