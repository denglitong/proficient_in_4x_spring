package com.denglitong.conf;

import java.util.TimerTask;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class CacheTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Run CacheTask");
    }
}
