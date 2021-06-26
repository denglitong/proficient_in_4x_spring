package com.denglitong.basic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        System.out.println(jobContext.getTrigger().getJobKey().getName() +
                " triggered.time is:" + new Date());
    }
}
