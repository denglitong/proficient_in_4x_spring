package com.denglitong.simplecache;

import com.denglitong.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
@Service("userService4")
public class UserService4 {

    public User findUser(String userId) {
        return getFromDB(userId);
    }

    public void removeUser(String userId) {
        System.out.println("real remove user." + userId);
    }

    private User getFromDB(String userId) {
        System.out.println("real querying db..." + userId);
        return new User(userId);
    }

    public static void main(String[] args) {
        String configPath = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        UserService4 userService4 = context.getBean(UserService4.class);

        String userId = "000100";
        System.out.println(userService4.findUser(userId));
        System.out.println(userService4.findUser(userId));
        userService4.removeUser(userId);
        System.out.println(userService4.findUser(userId));
        System.out.println(userService4.findUser(userId));
    }
}
