package com.denglitong.dynamic;

import com.denglitong.annotation.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class UserServiceTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:beans3.xml");

        UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
        System.out.println(userDAO);

        UserService userService1 = ctx.getBean("userService1", UserService.class);
        System.out.println(userService1);
    }
}
