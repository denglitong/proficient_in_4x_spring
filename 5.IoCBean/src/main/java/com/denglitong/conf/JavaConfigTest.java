package com.denglitong.conf;

import com.denglitong.annotation.LogDAO;
import com.denglitong.annotation.LogonService;
import com.denglitong.annotation.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class JavaConfigTest {

    public static void main(String[] args) {
        // AnnotationConfigApplicationContext 可直接基于 @Configuration 的类启动 Spring 容器
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConf.class, LogDAO.class);

        UserDAO userDAO = ctx.getBean(UserDAO.class);
        System.out.println(userDAO);

        LogonService logonService = ctx.getBean(LogonService.class);
        System.out.println(logonService);

        AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext();
        ctx2.register(AppConf.class, UserDAO.class, DaoConfig.class, ServiceConfig.class);
        ctx2.refresh();

        LogonService logonService2 = ctx2.getBean("logonService2", LogonService.class);
        System.out.println(logonService2);

        AnnotationConfigApplicationContext ctx3 = new AnnotationConfigApplicationContext(ServiceConfigImport.class);
        LogonService logonService3 = ctx2.getBean("logonService2", LogonService.class);
        System.out.println(logonService3);
    }
}
