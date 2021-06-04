package com.denglitong.conf;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class SystemInit {

    public SystemInit() {
        // 模拟从数据库中加载配置参数
        SystemSettings.SESSION_TIMEOUT = 10;
        SystemSettings.REFRESH_CYCLE = 100;
    }
}
