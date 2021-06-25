package com.denglitong.domain;

import java.io.Serializable;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/24
 */
public class Member extends User implements Serializable {

    private static final long serialVersionUID = 2240652328358638048L;

    public Member(String userId) {
        super(userId);
    }

    public Member(String id, String name) {
        super(id, name);
    }
}
