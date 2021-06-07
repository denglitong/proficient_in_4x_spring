package com.denglitong.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/7
 */
public class ApplicationContextEventTest {

    public static void main(String[] args) {
        String[] config = {"classpath:com/denglitong/event/beans.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        MailSender mailSender = ctx.getBean("mailSender", MailSender.class);
        mailSender.sendMail("deng.litong@foxmail.com");
    }
}
