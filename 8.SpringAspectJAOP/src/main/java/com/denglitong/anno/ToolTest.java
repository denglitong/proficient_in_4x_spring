package com.denglitong.anno;

import java.lang.reflect.Method;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public class ToolTest {

    public static void main(String[] args) {
        Class clazz = ForumService.class;

        for (Method method : clazz.getDeclaredMethods()) {
            NeedTest needTest = method.getAnnotation(NeedTest.class);
            if (needTest != null) {
                System.out.println(method.getName() + " needTest: " + needTest.value());
            }
        }
    }
}
