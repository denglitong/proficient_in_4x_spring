package com.denglitong.controller;

import com.denglitong.domain.User;
import com.denglitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/28
 */
@RestController
@RequestMapping("/rest/user")
public class RestUserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 使用 AsyncRestTemplate 可以以异步无阻塞的方式进行服务访问
    @RequestMapping("/api")
    public Callable<User> api() {
        System.out.println("=== api ===");
        return new Callable<User>() {
            @Override
            public User call() throws Exception {
                Thread.sleep(5 * 1000);
                User user = new User();
                user.setUserId("1");
                user.setUserName("haha");
                return user;
            }
        };
    }

}
