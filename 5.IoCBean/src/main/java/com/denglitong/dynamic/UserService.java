package com.denglitong.dynamic;

import com.denglitong.annotation.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userDAO=" + userDAO +
                '}';
    }
}
