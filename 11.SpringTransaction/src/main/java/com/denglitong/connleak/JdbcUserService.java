package com.denglitong.connleak;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/19
 */
@Service("jdbcUserService")
public class JdbcUserService {

    public static void asyncLogon(UserService userService, String userName) {
        UserServiceRunner runner = new UserServiceRunner(userService, userName);
        runner.start();
    }

    private static class UserServiceRunner extends Thread {
        private UserService userService;
        private String userName;

        public UserServiceRunner(UserService userService, String userName) {
            this.userService = userService;
            this.userName = userName;
        }

        public void run() {
            userService.logon(userName);
        }
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reportConn(BasicDataSource basicDataSource) {
        System.out.println("连接数[active:idle]-[" + basicDataSource.getNumActive() +
                ":" + basicDataSource.getNumIdle() + "]");
    }

    public static void main(String[] args) {
        String configPath = "classpath:com/denglitong/connleak/applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
        UserService userService = (UserService)context.getBean("userService");
        userService.setJdbcTemplate(jdbcTemplate);

        /*BasicDataSource basicDataSource = (BasicDataSource)context.getBean("datasource");

        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.asyncLogon(userService, "tom");
        JdbcUserService.sleep(1000);

        JdbcUserService.reportConn(basicDataSource);
        JdbcUserService.sleep(2000);

        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.asyncLogon(userService, "tom");
        JdbcUserService.sleep(500);

        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.sleep(2000);

        JdbcUserService.reportConn(basicDataSource);*/
        
        userService.logon("tom");
    }
}
