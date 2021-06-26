package com.denglitong.basic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jctx) throws JobExecutionException {
        Map dataMap = jctx.getJobDetail().getJobDataMap();
        String size = (String)dataMap.get("size");

        ApplicationContext ctx = (ApplicationContext)dataMap.get("applicationContext");
        System.out.println("size:" + size + ", className:" + ctx.getClass().getName());
    }
}
