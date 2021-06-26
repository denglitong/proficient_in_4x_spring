package com.denglitong.web;

import com.denglitong.timer.SimpleTimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/26
 */
public class StartCycleRunTask implements ServletContextListener {

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("WEB 应用程序启动...");
        timer = new Timer();
        TimerTask task = new SimpleTimerTask();
        timer.schedule(task, 1000L, 5000L);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("WEB 应用程序关闭...");
        // 在容器关闭时需手动关闭 Timer
        // 推荐使用 Spring 提供的 TimerFactoryBean/SchedulerFactoryBean
        timer.cancel();
    }
}
