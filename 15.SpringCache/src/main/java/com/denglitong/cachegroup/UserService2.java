package com.denglitong.cachegroup;

import com.denglitong.domain.Member;
import com.denglitong.domain.User;
import com.denglitong.domain.Visitor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
@Service("userService2")
public class UserService2 {

    private static final String USERS = "users";
    private static final String MEMBERS = "members";
    private static final String VISITORS = "visitors";

    public Map<Integer, User> userMap = new ConcurrentHashMap<>();

    {
        userMap.put(1, new Member("1", "w1"));
        userMap.put(2, new Visitor("2", "w2"));
    }

    // Caching 提供一组注解，可包含 @Cacheable, @CacheEvict, @CachePut
    @Caching(cacheable = {
            @Cacheable(cacheNames = MEMBERS,
                    key = "#obj.userId",
                    condition = "#obj instanceof T(com.denglitong.domain.User)"),
            @Cacheable(cacheNames = VISITORS,
                    key = "#obj.userId",
                    condition = "#obj instanceof T(com.denglitong.domain.User)"),
            @Cacheable(cacheNames = USERS, key = "#obj.userId")
    })
    public User getUser(User obj) {
        System.out.println("real query user." + obj.getUserId());
        return userMap.get(Integer.valueOf(obj.getUserId()));
    }

    @Cacheable(cacheNames = MEMBERS, key = "#userId")
    public Member getMember(String userId) {
        return (Member)userMap.get(Integer.valueOf(userId));
    }

    @UserSaveCache
    public User saveUser(User user) {
        System.out.println("save user." + user.getUserId());
        return user;
    }

    public static void main(String[] args) {
        String configPath = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        UserService2 userService2 = context.getBean(UserService2.class);

        /*System.out.println(userService2.getUser(new Member("1")));
        System.out.println(userService2.getUser(new Member("1")));
        System.out.println(userService2.getUser(new Visitor("2")));
        System.out.println(userService2.getUser(new Visitor("2")));

        System.out.println(userService2.getMember("1"));*/

        User user = new Member("1", "w1");
        userService2.saveUser(user);
        System.out.println(userService2.getUser(user));

        user.setUserId("2");
        System.out.println(userService2.getUser(user));
    }
}
