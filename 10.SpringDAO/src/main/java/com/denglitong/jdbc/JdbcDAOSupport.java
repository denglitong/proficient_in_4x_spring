package com.denglitong.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/13
 */
public class JdbcDAOSupport extends JdbcDaoSupport {

    {
        getJdbcTemplate().queryForObject("select 1", Integer.class);
    }
}
