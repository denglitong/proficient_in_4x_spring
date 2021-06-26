package com.denglitong.basic;

import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class JDBCJobStoreRunner {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        for (String triggerGroup : scheduler.getTriggerGroupNames()) {
            GroupMatcher<TriggerKey> groupMatcher = GroupMatcher.triggerGroupEquals(triggerGroup);
            for (TriggerKey triggerKey : scheduler.getTriggerKeys(groupMatcher)) {
                Trigger trigger = scheduler.getTrigger(triggerKey);
                // 根据名称判断恢复某个触发器
                if (trigger instanceof SimpleTrigger
                        && triggerKey.getGroup().equals("tgroup_3")
                        && triggerKey.getName().equals("trigger_3")) {
                    System.out.println("rescheduleJob " + triggerKey);
                    scheduler.rescheduleJob(triggerKey, trigger);
                }
            }
        }

        // 最后，需要开启调度
        scheduler.start();
        System.out.println("started");
    }
}
