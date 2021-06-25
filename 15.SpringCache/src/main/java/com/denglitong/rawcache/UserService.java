package com.denglitong.rawcache;

import com.denglitong.domain.User;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
public class UserService {

    private CacheManager<User> cacheManager;

    public UserService() {
        cacheManager = new CacheManager<>();
    }

    public User getUserById(String userId) {
        User result = cacheManager.get(userId);
        if (result != null) {
            System.out.println("get user from cache..." + userId);
            return result;
        }
        result = getFromDB(userId);
        if (result != null) {
            cacheManager.addOrUpdate(userId, result);
        }
        return result;
    }

    public void reload() {
        cacheManager.evictAll();
    }

    private User getFromDB(String userId) {
        System.out.println("real querying db..." + userId);
        return new User(userId);
    }

    public static void main(String[] args) {
        UserService userService = new UserService();

        userService.getUserById("001001");
        userService.getUserById("001001");

        userService.reload();
        System.out.println("after reload...");

        userService.getUserById("001001");
        userService.getUserById("001001");
    }
}
