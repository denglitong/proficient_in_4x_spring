package com.denglitong.proxy;

import com.denglitong.introduce.Monitorable;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/7
 */
public class ForumServiceImpl implements ForumService {

    @Override
    public void removeTopic(int topicId) {
        // PerformanceMonitor.begin("com.denglitong.proxy.ForumServiceimpl.removeTopic");
        System.out.println("模拟删除 Topic 记录：" + topicId);
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // PerformanceMonitor.end();
    }

    @Override
    public void removeForum(int forumId) {
        // PerformanceMonitor.begin("com.denglitong.proxy.ForumServiceimpl.removeForum");
        System.out.println("模拟删除 Forum 记录：" + forumId);
        try {
            Thread.sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // PerformanceMonitor.end();
    }
}
