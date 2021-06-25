package com.denglitong.domain;

import java.io.Serializable;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
public class Visitor extends User implements Serializable {

    private static final long serialVersionUID = -2036855060913340703L;

    public Visitor(String userId) {
        super(userId);
    }

    public Visitor(String userId, String userName) {
        super(userId, userName);
    }
}
