package com.denglitong.dao;

import com.denglitong.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
@Repository
public class ForumDao {

    private JdbcTemplate jdbcTemplate;

    public void addForum(final Forum forum) {
        String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES (?,?)";
        Object[] params = new Object[]{forum.getForumName(), forum.getForumDesc()};
        jdbcTemplate.update(sql, params);
    }

    public Forum getForum(final int forumId) {
        String sql = "SELECT forum_name, forum_desc FROM t_forum WHERE forum_id =? LIMIT 1";
        final Forum forum = new Forum();
        jdbcTemplate.query(sql, new Object[]{forumId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                forum.setForumId(forumId);
                forum.setForumName(rs.getString("forum_name"));
                forum.setForumDesc(rs.getString("forum_desc"));
            }
        });
        return forum;
    }

    public void updateForum(final Forum forum) {
        String sql = "UPDATE t_forum SET forum_name =?, forum_desc =? WHERE forum_id =?";
        Object[] params = new Object[]{forum.getForumName(), forum.getForumDesc(), forum.getForumId()};
        jdbcTemplate.update(sql, params);
    }

    public int getForumNum() {
        String sql = "SELECT forum_id FROM t_forum";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
