package com.denglitong.timer;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class TimerRunner {

    public static void main(String[] args) throws SchedulerException {
        // Timer timer = new Timer();
        // TimerTask task = new SimpleTimerTask();
        // timer.schedule(task, 1000L, 1000L);

        // main 函数运行后，即 Spring 容器启动后，
        // 会自动调度 ScheduledExecutorFactoryBean 修饰的 task
        String configPath = "classpath:applicationContextTimer.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        Runnable runnable = (Runnable)context.getBean("timerTask");
        runnable.run();
    }

}
