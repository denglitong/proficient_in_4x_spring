package com.denglitong.simple;

import javax.annotation.Resource;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class Bar {

    private Foo foo;

    public Bar() {
    }

    public Bar(Foo foo) {
        this.foo = foo;
    }

    public Foo getFoo() {
        return foo;
    }

    public void setFoo(Foo foo) {
        this.foo = foo;
    }
}
