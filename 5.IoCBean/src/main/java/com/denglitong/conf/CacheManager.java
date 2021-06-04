package com.denglitong.conf;

import java.util.Timer;
import java.util.TimerTask;

/**
 * CacheManager 依赖 SystemSettings，
 * 而 SystemSettings 最好是由 SystemInit 完成前置初始化，
 * 所以 CacheManager 依赖于 SystemSettings, SystemInit,
 * 在 <bean> 中使用 depends-on 指定依赖关系
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class CacheManager {

    private static Integer count = 0;

    public CacheManager() {
        Timer timer = new Timer();
        TimerTask cacheTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Run CacheTask");
                if (count++ > 3) {
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(cacheTask, 0, SystemSettings.REFRESH_CYCLE);
        System.out.println("Schedule cache task with cycle: " + SystemSettings.REFRESH_CYCLE);
    }
}
