package com.denglitong.cachegroup;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.*;

/**
 * 自定义注解，简化注解方法模板代码
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
@Caching(
        put = {
                @CachePut(cacheNames = "users", key = "#user.userId"),
                @CachePut(cacheNames = "users", key = "#user.userName")
        }
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface UserSaveCache {
}
