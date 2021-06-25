package com.denglitong.domain;

import java.io.Serializable;

/**
 * Java 对象的缓存和序列化是息息相关的，
 * 一般情况下，被缓存的实体类需要实现 Serializable 接口
 * <p>
 * 实体类始终实现 Serializable 接口是一个好的编程习惯
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3411228600613112694L;

    private String userId;

    private String userName;

    private int age;

    public User() {}

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public User(String userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
