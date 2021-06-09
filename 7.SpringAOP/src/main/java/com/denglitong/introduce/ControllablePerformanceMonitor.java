package com.denglitong.introduce;

import com.denglitong.proxy.PerformanceMonitor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor
        implements Monitorable {

    // 使用 ThreadLocal 类型，让每个线程单独使用一个状态，保持线程安全
    private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<>();

    @Override
    public void setMonitorActive(boolean active) {
        MonitorStatusMap.set(active);
    }

    // 拦截方法
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;

        if (MonitorStatusMap != null && MonitorStatusMap.get() != null &&
                MonitorStatusMap.get()) {
            PerformanceMonitor.begin(mi.getClass().getName() + "." + mi.getMethod().getName());
            obj = super.invoke(mi);
            PerformanceMonitor.end();
        } else {
            obj = super.invoke(mi);
        }
        return obj;
    }
}
