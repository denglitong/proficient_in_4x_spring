package com.denglitong.service;

import com.denglitong.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.testng.Assert.*;

/**
 * TestNG 和 Junit 相比有了重大的改进
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/2
 */
@ContextConfiguration("classpath*:/bbs-context.xml") // 启动 Spring 容器
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertFalse(b2);
    }

    @Test
    public void findUserByUserName() {
        User user = userService.findUserByUserName("admin");
        System.out.println(user);
        assertEquals(user.getUserName(), "admin");
    }

    /**
     * 默认 text content 的事务是开启 Rollback，如果希望提交则需要声明 @Rollback(false)
     */
    @Test
    @Rollback(value = false)
    public void addLoginLog() {
        User user = userService.findUserByUserName("admin");
        System.out.println(user);
        user.setLastIp("10.23.25.16");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
