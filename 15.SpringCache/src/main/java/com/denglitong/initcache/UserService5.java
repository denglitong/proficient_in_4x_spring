package com.denglitong.initcache;

import com.denglitong.domain.Member;
import com.denglitong.domain.User;
import com.denglitong.domain.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
@Service("userService5")
public class UserService5 {

    private static final String USER_CACHE = "users";

    private final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    {
        userMap.put(1, new Member("1", "w1"));
        userMap.put(2, new Visitor("2", "w2"));
    }

    private CacheManager cacheManager;

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 注入 cacheManager 调用 getCache() 获取缓存列表，然后初始化缓存列表
     * <p>
     * PostConstruct 需要有 component-scan 扫描才能生效的
     */
    @PostConstruct
    public void init() {
        System.out.println("post construct init");
        Cache userCache = cacheManager.getCache(USER_CACHE);
        userMap.forEach(userCache::put);
    }

    @Cacheable(cacheNames = USER_CACHE)
    public User getUser(int id) {
        System.out.println("User with id " + id + " requested.");
        return userMap.get(id);
    }

    public static void main(String[] args) {
        String configPath = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        UserService5 userService5 = context.getBean(UserService5.class);

        System.out.println(userService5.getUser(1));
        System.out.println(userService5.getUser(2));
        System.out.println(userService5.getUser(3));
    }
}
