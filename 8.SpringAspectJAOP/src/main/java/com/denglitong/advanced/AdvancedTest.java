package com.denglitong.advanced;

import com.denglitong.fun.NaiveWaiter;
import com.denglitong.fun.SmartSeller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public class AdvancedTest {

    public static void main(String[] args) {
        String configPath = "classpath:com/denglitong/advanced/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        NaiveWaiter naiveWaiter = ctx.getBean("naiveWaiter", NaiveWaiter.class);
        naiveWaiter.greetTo("John");

        naiveWaiter.smile("Tom", 3);

        SmartSeller seller = ctx.getBean("seller", SmartSeller.class);
        seller.sell("Beer", "John");
        seller.checkBill(1);
        seller.checkBill(2);
    }
}
