package com.denglitong.proxy;

import java.lang.reflect.Proxy;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/7
 */
public class ForumServiceTest {

    public static void main(String[] args) {
        // 非 AOP 方式
        // ForumService forumService = new ForumServiceImpl();
        // forumService.removeForum(10);
        // forumService.removeForum(12);

        ForumService target = new ForumServiceImpl();
        PerformanceHandler handler = new PerformanceHandler(target);
        ForumService proxy = (ForumService)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler
        );
        proxy.removeTopic(10);
        proxy.removeForum(12);

        CglibProxy proxy1 = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl)proxy1.getProxy(ForumServiceImpl.class);
        forumService.removeTopic(10);
        forumService.removeForum(12);
    }
}
