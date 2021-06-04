package com.denglitong.simple;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class Foo {

    private Bar bar;

    public Foo() {
    }

    public Foo(Bar bar) {
        this.bar = bar;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
