package com.denglitong.basic;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class CronTriggerRunner {

    public static void main(String[] args) throws ParseException, SchedulerException {
        JobDetail jobDetail = new JobDetailImpl("job_2", "jgroup_2", SimpleJob.class);

        CronTriggerImpl cronTrigger = new CronTriggerImpl("trigger_2", "tgroup_2");
        CronExpression cronExpression = new CronExpression("0/5 * * * * ?");
        cronTrigger.setCronExpression(cronExpression);

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }
}
