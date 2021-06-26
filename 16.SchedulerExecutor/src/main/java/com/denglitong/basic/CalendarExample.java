package com.denglitong.basic;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.Calendar;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class CalendarExample {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 法定节日是以每年为周期的
        AnnualCalendar holidays = new AnnualCalendar();

        // 构造五一劳动节的 calendar
        Calendar laborDay = new GregorianCalendar();
        laborDay.add(Calendar.MONTH, 5);
        laborDay.add(Calendar.DATE, 1);

        Calendar nationDay = new GregorianCalendar();
        nationDay.add(Calendar.MONTH, 10);
        nationDay.add(Calendar.DATE, 1);

        ArrayList<Calendar> calendars = new ArrayList<>();
        calendars.add(laborDay);
        calendars.add(nationDay);
        holidays.setDaysExcluded(calendars);

        // 设置日历时间（除去了特定日期），即该调度器只在日历时间范围内进行触发器规则
        scheduler.addCalendar("holidays", holidays, false, false);

        // 4月 1 号上午 10 点
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Date runDate = Date.from(LocalDateTime.of(year, 4, 1, 10, 0)
                .atZone(ZoneId.of("Asia/Shanghai")).toInstant());

        JobDetail jobDetail = new JobDetailImpl("job_3", "jgroup_3", SimpleJob.class);

        // 从4.1号10点开始，每小时触发一次
        SimpleTriggerImpl trigger = new SimpleTriggerImpl("trigger_3", "tgroup_3",
                runDate, null,
                // SimpleTrigger.REPEAT_INDEFINITELY, 60L * 60L * 1000L);
                SimpleTrigger.REPEAT_INDEFINITELY, 2L * 1000L);
        // 触发器指定日历时间范围，这里的 calendarName 在 scheduler 中绑定
        trigger.setCalendarName("holidays");

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
