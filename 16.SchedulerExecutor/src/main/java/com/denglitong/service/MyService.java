package com.denglitong.service;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class MyService {

    public void doJob() {
        System.out.println("in MyService.doJob().");
    }

    public static void main(String[] args) throws SchedulerException {
        String configPath = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        Scheduler scheduler = (Scheduler)context.getBean("scheduler");
        scheduler.start();
    }
}
