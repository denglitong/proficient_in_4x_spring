package com.denglitong.dao;

import com.denglitong.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
@Repository
public class PostDao {

    private JdbcTemplate jdbcTemplate;

    public void addPost(final Post post) {
        String sql = "INSERT INTO t_post(topic_id, post_text) " +
                " VALUES(?, ?)";
        Object[] params = new Object[]{post.getTopicId(), post.getPostText()};
        jdbcTemplate.update(sql, params);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
