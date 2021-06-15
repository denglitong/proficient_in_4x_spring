package com.denglitong.dao;

import com.denglitong.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
@Repository
public class TopicDao {

    private JdbcTemplate jdbcTemplate;

    public void addTopic(final Topic topic) {
        String sql = "INSERT INTO t_topic(topic_title) VALUES (?)";
        Object[] params = new Object[]{topic.getTopicTitle()};
        jdbcTemplate.update(sql, params);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
