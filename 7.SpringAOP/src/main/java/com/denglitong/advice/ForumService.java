package com.denglitong.advice;

import java.sql.SQLException;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class ForumService {

    public void removeForum(int forumId) {
        System.out.println("模拟删除 forumId:" + forumId);
        throw new RuntimeException("运行异常。");
    }

    public void updateForum(Forum forum) throws SQLException {
        System.out.println("模拟更新 forum:" + forum);
        throw new SQLException("数据更新操作异常");
    }
}
