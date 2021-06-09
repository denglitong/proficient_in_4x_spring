package com.denglitong.advisor;

import com.denglitong.introduce.ControllablePerformanceMonitor;
import com.denglitong.introduce.ForumService;
import com.denglitong.introduce.Monitorable;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class AdvisorTest {

    public static void main(String[] args) {
        Waiter target = new Waiter();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Waiter());

        GreetingAdvisor advisor = new GreetingAdvisor();
        advisor.setAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvisor(advisor);

        Waiter proxy = (Waiter)proxyFactory.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");

        Seller target1 = new Seller();
        ProxyFactory proxyFactory1 = new ProxyFactory();
        proxyFactory1.setTarget(target1);
        proxyFactory1.addAdvisor(advisor);

        Seller proxy1 = (Seller)proxyFactory1.getProxy();
        proxy1.greetTo("John");

        // xml 方式配置 bean
        String[] configs = {"classpath:com/denglitong/advisor/beans.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
        Waiter waiter = ctx.getBean("waiter", Waiter.class);
        waiter.greetTo("John");
        waiter.serveTo("Tom");
        Seller seller = ctx.getBean("seller", Seller.class);
        seller.greetTo("John");

        System.out.println("**********************************");
        Waiter waiter1 = ctx.getBean("waiter1", Waiter.class);
        waiter1.greetTo("John");
        waiter1.serveTo("Tom");
        Seller seller1 = ctx.getBean("seller1", Seller.class);
        seller1.greetTo("John");

        System.out.println("**********************************");
        Waiter waiter2 = ctx.getBean("waiter2", Waiter.class);
        waiter2.greetTo("John");
        waiter2.greetTo("Tom");
        waiter2.greetTo("Leon");

        Waiter target2 = new Waiter();
        ProxyFactory proxyFactory2 = new ProxyFactory(target2);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor();
        advisor2.setPointcut(new GreetingDynamicPointcut());
        advisor2.setAdvice(new GreetingBeforeAdvice());
        proxyFactory2.addAdvisor(advisor2);

        System.out.println("**********************************");
        Waiter proxy2 = (Waiter)proxyFactory2.getProxy();
        proxy2.greetTo("John");
        proxy2.greetTo("Tom");
        proxy2.greetTo("Leon");

        System.out.println("**********************************");
        DefaultPointcutAdvisor advisor3 = new DefaultPointcutAdvisor();
        GreetingControlFlowPointcut controlFlowPointcut = new GreetingControlFlowPointcut(WaiterDelegate.class, "service");
        advisor3.setPointcut(controlFlowPointcut);
        advisor3.setAdvice(new GreetingBeforeAdvice());

        Waiter target3 = new Waiter();
        ProxyFactory proxyFactory3 = new ProxyFactory(target3);
        proxyFactory3.addAdvisor(advisor3);
        proxyFactory3.setProxyTargetClass(true);

        // 对 waiter3 织入了在 WaiterDelegate#service 上下文的流程切面
        // 如果 WaiterDelegate#service 上下文还有其他的 target 需要织入流程切面，步骤同 waiter3
        // 流程切面和动态切面两者都需要在运行期判断动态的环境：
        //   对流程切面来说，代理对象在每次调用目标类时都需要判断方法堆栈中是否有满足流程切点要求的方法
        //   因此，流程切面对性能的影响也很大（慢 5 倍）
        Waiter waiter3 = (Waiter)proxyFactory3.getProxy();

        Seller target4 = new Seller();
        ProxyFactory proxyFactory4 = new ProxyFactory(target4);
        proxyFactory4.addAdvisor(advisor3);
        proxyFactory4.setProxyTargetClass(true);
        Seller seller4 = (Seller)proxyFactory4.getProxy();

        WaiterDelegate waiterDelegate = new WaiterDelegate();
        waiterDelegate.setWaiter(waiter3);
        waiterDelegate.setSeller(seller4);
        waiterDelegate.service("John");

        System.out.println("**********************************");
        Waiter waiter4 = ctx.getBean("waiter3", Waiter.class);
        WaiterDelegate waiterDelegate1 = new WaiterDelegate();
        waiterDelegate1.setWaiter(waiter4);
        waiterDelegate1.setSeller(new Seller());
        waiterDelegate1.service("Tom");

        System.out.println("**********************************");
        Waiter waiter5 = ctx.getBean("waiter4", Waiter.class);
        WaiterDelegate waiterDelegate2 = new WaiterDelegate();
        waiterDelegate2.setWaiter(waiter5);
        waiterDelegate2.setSeller(seller4);
        waiter5.greetTo("John");
        waiter5.serveTo("John");
        waiterDelegate2.service("John");

        System.out.println("**********************************");
        DefaultPointcutAdvisor composableAdvisor = new DefaultPointcutAdvisor();
        composableAdvisor.setPointcut(new GreetingComposablePointcut().getIntersectionPointcut());
        composableAdvisor.setAdvice(new GreetingBeforeAdvice());
        ProxyFactory proxyFactory5 = new ProxyFactory(new Waiter());
        proxyFactory5.addAdvisor(composableAdvisor);
        proxyFactory5.setProxyTargetClass(true);

        Waiter waiter6 = (Waiter)proxyFactory5.getProxy();
        WaiterDelegate waiterDelegate3 = new WaiterDelegate();
        waiterDelegate3.setWaiter(waiter6);
        waiterDelegate3.setSeller(seller4);
        waiter6.greetTo("Tom");
        waiter6.serveTo("Tom");
        waiterDelegate3.service("Tom");

        // <bean> 方式配置
        ForumService forumService = ctx.getBean("forumService", ForumService.class);
        Monitorable proxy3 = (Monitorable)forumService;
        proxy3.setMonitorActive(true);
        forumService.removeTopic(10);

        // 不使用 <bean> 方式配置
        DefaultIntroductionAdvisor introductionAdvisor = new DefaultIntroductionAdvisor(new ControllablePerformanceMonitor());
        ForumService target6 = new ForumService();
        ProxyFactory proxyFactory6 = new ProxyFactory(target6);
        proxyFactory6.addAdvisor(introductionAdvisor);
        proxyFactory6.setProxyTargetClass(true);

        ForumService forumService6 = (ForumService)proxyFactory6.getProxy();
        Monitorable proxy6 = (Monitorable)forumService6;
        proxy6.setMonitorActive(true);
        forumService6.removeForum(12);
    }
}
