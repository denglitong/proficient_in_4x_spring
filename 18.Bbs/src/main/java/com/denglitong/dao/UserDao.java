package com.denglitong.dao;

import com.denglitong.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Repository
public class UserDao extends BaseDao<User> {

    private static final String GET_USER_BY_USER_NAME =
            " from User where userName = ?";
    private static final String QUERY_USER_BY_USER_NAME =
            " from User where userName like ?";

    public User getUserByUserName(String userName) {
        return find(GET_USER_BY_USER_NAME, userName).stream().findFirst().orElse(null);
    }

    public List<User> queryUserByUserName(String userName) {
        return find(QUERY_USER_BY_USER_NAME, userName + "%");
    }
}
