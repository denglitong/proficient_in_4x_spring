package com.denglitong.dao;

import com.denglitong.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/1
 */
@Repository
public class UserDao {

    // SQL语句定义位静态变量更具有可读性
    private static final String MATCH_COUNT_SQL = " SELECT COUNT(*) FROM t_user " +
            " WHERE user_name =? and password =? ";
    private static final String SELECT_USER_SQL = " SELECT * " +
            " FROM t_user WHERE user_name =? LIMIT 1 ";
    private static final String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET " +
            " last_visit =?, last_ip =?, credits =? WHERE user_id =? ";

    private JdbcTemplate jdbcTemplate;

    /**
     * 自动注入 JdbcTemplate 的 Bean
     *
     * @param jdbcTemplate
     */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemplate.query(SELECT_USER_SQL, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(java.sql.ResultSet rs) throws SQLException {
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(rs.getInt("credits"));
                user.setPassword(rs.getString("password"));
                // getDate(), getTime()
                user.setLastVisit(rs.getTimestamp("last_visit"));
                user.setLastIp(rs.getString("last_ip"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,
                user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId());
    }
}
