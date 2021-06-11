package com.denglitong.schema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class SchemaTest {

    public static void main(String[] args) {
        String configPath = "classpath:com/denglitong/schema/beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        NaiveWaiter naiveWaiter = context.getBean("naiveWaiter", NaiveWaiter.class);
        NaughtyWaiter naughtyWaiter = context.getBean("naughtyWaiter", NaughtyWaiter.class);
        naiveWaiter.greetTo("John");
        naughtyWaiter.greetTo("Tom");

        SmartSeller smartSeller = context.getBean("smartSeller", SmartSeller.class);
        int retVal = smartSeller.sell("Apple", "John");
        System.out.println("-- retVal: " + retVal + " --");

        naiveWaiter.serveTo("Tom");

        Seller seller = (Seller)naiveWaiter;
        seller.sell("Yellow apple", "John");

        // smartSeller.checkBill(1);
        // smartSeller.checkBill(2);

        naiveWaiter.smile("John", 3);
    }
}
