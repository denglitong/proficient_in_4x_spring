package com.denglitong.dao;

import com.denglitong.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/1
 */
@Repository
public class LoginLogDao {

    private static final String INSERT_LOGIN_LOG_SQL = " INSERT INTO t_login_log " +
            " (user_id, ip, login_datetime) VALUES (?, ?, ?) ";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertLoginLog(LoginLog loginLog) {
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate());
    }
}
