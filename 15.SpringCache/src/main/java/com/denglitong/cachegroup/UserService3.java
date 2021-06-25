package com.denglitong.cachegroup;

import com.denglitong.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * CacheConfig 提供类级别的注解，简化方法上的注解模板
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
@Service("userService3")
@CacheConfig(cacheNames = "users")
public class UserService3 {

    public static final String FIND_CACHE_A = "findA";

    @Cacheable(key = "getTarget().FIND_CACHE_A + #userId")
    public User findA(String userId) {
        return getUser(userId);
    }

    @Cacheable(key = "getMethodName()+#userId")
    public User findB(String userId) {
        return getUser(userId);
    }

    private User getUser(String userId) {
        System.out.println("real query user." + userId);
        return new User(userId);
    }

    public static void main(String[] args) {
        String configPath = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        UserService3 userService3 = context.getBean(UserService3.class);

        System.out.println(userService3.findA("1"));
        System.out.println(userService3.findA("1"));
        System.out.println(userService3.findB("1"));
        System.out.println(userService3.findB("1"));
    }
}
