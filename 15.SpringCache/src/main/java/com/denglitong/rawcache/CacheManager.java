package com.denglitong.rawcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
public class CacheManager<T> {

    private Map<String, T> cache = new ConcurrentHashMap<>();

    public T get(String key) {
        return cache.get(key);
    }

    public void addOrUpdate(String key, T value) {
        cache.put(key, value);
    }

    public void evict(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    public void evictAll() {
        cache.clear();
    }
}
