package com.denglitong.exception;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/2
 */
public class UserExistException extends Exception {

    public UserExistException(String message) {
        super(message);
    }
}
