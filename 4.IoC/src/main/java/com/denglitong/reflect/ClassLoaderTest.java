package com.denglitong.reflect;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // AppClassLoader
        System.out.println("current loader: " + loader);
        // ExtClassLoader
        System.out.println("parent loader: " + loader.getParent());
        // 根装载器在 Java 中访问不到它的句柄，所以返回 null
        System.out.println("grandparent loader: " + loader.getParent().getParent());
    }

}
