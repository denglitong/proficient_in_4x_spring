package com.denglitong.introduce;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class IntroduceTest {

    public static void main(String[] args) {
        ForumService forumService = new ForumService();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(forumService);
        proxyFactory.setInterfaces(Monitorable.class);
        proxyFactory.addAdvice(new ControllablePerformanceMonitor());
        // 引介增强一定要通过创建子类来生成代理，所以需要显式指定使用 CGLib 方式
        proxyFactory.setProxyTargetClass(true);

        ForumService proxy = (ForumService)proxyFactory.getProxy();
        proxy.removeTopic(10);

        Monitorable monitorable = (Monitorable)proxy;
        monitorable.setMonitorActive(true);
        // CglibAopProxy$CglibMethodInvocation.removeTopic
        proxy.removeTopic(10);

        String[] configs = {"classpath:com/denglitong/introduce/beans.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
        ForumService forumService1 = ctx.getBean("forumService", ForumService.class);
        forumService1.removeForum(12);

        Monitorable monitorable1 = (Monitorable)forumService1;
        monitorable1.setMonitorActive(true);
        forumService1.removeForum(12);
        
    }
}
