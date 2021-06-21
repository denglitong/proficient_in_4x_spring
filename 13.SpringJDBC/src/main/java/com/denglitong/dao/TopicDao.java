package com.denglitong.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/21
 */
@Repository
public class TopicDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 注意 RowSet 会一次性装载完所有数据；
    // 不像 ResultSet 分批次返回一批数据（批次行数为 fetchSize）
    public SqlRowSet getTopicRowSet(int userId) {
        String sql = "SELECT topic_id,topic_title FROM t_topic WHERE user_id = ?";
        Object[] params = {userId};
        return jdbcTemplate.queryForRowSet(sql, params);
    }
}
