package com.denglitong.simplecache;

import com.denglitong.domain.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 缓存实战：
 * - 非 public 方法不能被 AOP 增强，缓存注解无效
 * - 基于 Proxy 的 AOP 增强不能代理内部调用，缓存方法需要直接被外部调用
 * - @CacheEvict 默认为方法执行后触发，如果方法抛异常那么将不会触发
 * - 开发过程中缓存服务器未就绪时，调试代码须禁用缓存
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
@Service("userService")
public class UserService {

    private static final String USERS_CACHE_NAME = "users";

    /**
     * 1.缓存定义：确定需要缓存的方法和缓存策略
     * 2.缓存配置：配置缓存管理器（applicationContext.xml）
     * 默认情况下，缓存抽象使用方法签名及参数值作为键，返回值作为值，可通过 key 属性指定键
     * <p>
     * Cacheable 执行函数前会检查是否存在缓存，存在则直接返回缓存
     * - 缓存名称 cacheNames 是必需的，在 Spring 配置文件中定义必须指定一个
     * - 自定义键 key
     * - 自定义键生成器 keyGenerator
     * - 带条件的缓存 condition, unless
     *
     * @param userId
     * @return
     */
    @Cacheable(cacheNames = USERS_CACHE_NAME, key = "#userId")
    public User getUserWithCache(String userId) {
        System.out.println("real query user." + userId);
        return getFromDB(userId);
    }

    // 执行前不会检查缓存，而是每次都执行该函数将返回值更新缓存
    // 同一个方法不能同时使用 cacheable 和 cacheput 注解
    @CachePut(cacheNames = USERS_CACHE_NAME, key = "#userId")
    public User getUserAndUpdateCache(String userId) {
        System.out.println("real query user." + userId);
        return getFromDB(userId);
    }

    // allEntries = true 清楚缓存列表里的所有内容，此时将会忽略 key
    // beforeInvocation = true 在执行方法执行触发清楚缓存，防止方法抛异常后缓存未触发清楚
    @CacheEvict(cacheNames = USERS_CACHE_NAME, key = "#userId", beforeInvocation = true)
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

        UserService userService = context.getBean("userService", UserService.class);

        String userId1 = "000100";
        String userId2 = "000200";
        System.out.println("user1 first query...");
        System.out.println(userService.getUserWithCache(userId1));
        System.out.println("user1 second query...");
        System.out.println(userService.getUserWithCache(userId1));

        // System.out.println("user2 first query...");
        // System.out.println(userService.getUserWithCache(userId2));
        // System.out.println("user2 second query...");
        // System.out.println(userService.getUserWithCache(userId2));

        // System.out.println(userService.getUserAndUpdateCache(userId1));
        // System.out.println(userService.getUserWithCache(userId1));
        //
        // System.out.println(userService.getUserAndUpdateCache(userId1));
        // System.out.println(userService.getUserWithCache(userId1));
        //
        // userService.removeUser(userId1);
        // System.out.println(userService.getUserWithCache(userId1));
        // System.out.println(userService.getUserWithCache(userId1));
    }
}
