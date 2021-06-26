package com.denglitong.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/25
 */
public class SimpleTimerTask extends TimerTask {

    private int count = 0;

    @Override
    public void run() {
        System.out.println("execute task." + count);
        Date exeTime = new Date(scheduledExecutionTime());
        System.out.println("scheduleExecutionTime: " + exeTime);
        if (++count > 3) {
            cancel();
        }
    }
}
