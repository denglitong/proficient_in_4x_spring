package com.denglitong.basic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/14
 */
public class SimpleThreadLocal {

    private Map valueMap = Collections.synchronizedMap(new HashMap<>());

    public void set(Object newValue) {
        valueMap.put(Thread.currentThread(), newValue);
    }

    public Object get() {
        Thread currentThread = Thread.currentThread();
        Object obj = valueMap.get(currentThread);
        if (obj == null && !valueMap.containsKey(currentThread)) {
            obj = initialValue();
            valueMap.put(currentThread, obj);
        }
        return obj;
    }

    public void remove() {
        valueMap.remove(Thread.currentThread());
    }

    public Object initialValue() {
        return null;
    }
}
