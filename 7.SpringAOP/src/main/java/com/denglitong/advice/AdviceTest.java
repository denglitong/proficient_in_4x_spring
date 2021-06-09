package com.denglitong.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class AdviceTest {

    public static void main(String[] args) throws Throwable {
        NaiveWaiter naiveWaiter = new NaiveWaiter();
        naiveWaiter.greetTo("John");
        naiveWaiter.serveTo("John");

        Waiter target = new NaiveWaiter();
        BeforeAdvice advice = new GreetingBeforeAdvice();
        // Spring 提供的代理工场
        ProxyFactory proxyFactory = new ProxyFactory();
        // 设置代理目标
        proxyFactory.setTarget(target);
        // 为代理目标添加增强（可添加多个，调用顺序与添加顺序一致）
        proxyFactory.addAdvice(advice);
        proxyFactory.addAdvice(new GreetingAfterAdvice());
        // 指定目标接口，则 ProxyFactory 将使用 JdkDynamicAopProxy 创建代理
        // proxyFactory.setInterfaces(Waiter.class.getInterfaces());
        // 让 ProxyFactory 启动优化代理方式，将使用 Cglib2AopProxy 创建代理
        // proxyFactory.setOptimize(true);
        // proxyTargetClass：是否第类进行代理（而不是接口），设置为 true 之后将采用 CGLib 动态代理，并忽略 proxyInterfaces 属性
        // proxyFactory.setProxyTargetClass(true);
        // 生成代理实例
        Waiter proxy = (Waiter)proxyFactory.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");

        String[] configs = {"classpath:com/denglitong/advice/beans.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
        Waiter waiter = ctx.getBean("waiter", Waiter.class);
        waiter.greetTo("John");
        waiter.serveTo("Tom");

        System.out.println("**********************************************");
        Waiter target1 = new NaiveWaiter();
        ProxyFactory proxyFactory1 = new ProxyFactory();
        proxyFactory1.setTarget(target1);
        proxyFactory1.addAdvice(new GreetingInterceptor());
        Waiter proxy1 = (Waiter)proxyFactory1.getProxy();
        proxy1.greetTo("John");
        proxy1.serveTo("Tom");

        System.out.println("**********************************************");
        Waiter waiter1 = ctx.getBean("waiter1", Waiter.class);
        waiter1.greetTo("John");
        waiter1.serveTo("Tom");

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        ForumService forumService = new ForumService();
        ProxyFactory proxyFactory2 = new ProxyFactory();
        proxyFactory2.setTarget(forumService);
        proxyFactory2.addAdvice(new TransactionThrowsAdvice());
        ForumService proxy2 = (ForumService)proxyFactory2.getProxy();
        try {
            proxy2.updateForum(new Forum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            proxy2.removeForum(1012);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ForumService forumService1 = ctx.getBean("forumService", ForumService.class);
        try {
            forumService1.updateForum(new Forum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            forumService1.removeForum(1012);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
