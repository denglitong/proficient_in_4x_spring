package com.denglitong.basic;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class SimpleTriggerRunner {

    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = new JobDetailImpl("job_1", "jgroup_1", SimpleJob.class);

        SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl("trigger_1", "tgroup_1");
        simpleTrigger.setStartTime(new Date()); // 从当前时间开始
        simpleTrigger.setRepeatInterval(2000); // 间隔 2 秒执行一次
        simpleTrigger.setRepeatCount(3); // 重复执行 3 次

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
    }
}
