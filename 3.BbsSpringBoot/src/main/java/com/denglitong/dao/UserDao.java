package com.denglitong.dao;

import com.denglitong.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/2
 */
@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private static final String MATCH_COUNT_SQL = " SELECT COUNT(*) FROM t_user " +
            " WHERE user_name =? and password = ? ";
    private static final String FIND_BY_USER_NAME_SQL = " SELECT * FROM t_user " +
            " WHERE user_name =? ";
    private static final String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET " +
            " last_ip =?, last_visit =?, credits =? WHERE user_id = ? ";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(String userName) {
        final User user = new User();
        jdbcTemplate.query(FIND_BY_USER_NAME_SQL, new Object[]{userName}, new RowCallbackHandler() {
            @Override
            public void processRow(java.sql.ResultSet rs) throws SQLException {
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(rs.getInt("credits"));
                user.setLastIp(rs.getString("last_ip"));
                user.setLastVisit(rs.getTimestamp("last_visit"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,
                user.getLastIp(), user.getLastVisit(), user.getCredits(), user.getUserId());
    }
}
