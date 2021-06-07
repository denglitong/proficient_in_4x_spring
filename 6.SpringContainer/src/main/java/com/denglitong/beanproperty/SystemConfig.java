package com.denglitong.beanproperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class SystemConfig {

    private Integer sessionTimeout;

    private Integer maxTabPageNum;

    private JdbcTemplate jdbcTemplate;

    /**
     *
     */
    public void initFromDB() {
        // 模拟从数据库中读取配置值
        Integer sessionTimeout = jdbcTemplate.queryForObject(
                "select property_value from sys_config where property_key = ?",
                new Object[]{"session_timeout"}, Integer.class);
        this.sessionTimeout = sessionTimeout;

        Integer maxTabPageNum = jdbcTemplate.queryForObject(
                "select property_value from sys_config where property_key = ?",
                new Object[]{"max_tab_page_num"}, Integer.class);
        this.maxTabPageNum = maxTabPageNum;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Integer getMaxTabPageNum() {
        return maxTabPageNum;
    }

    public void setMaxTabPageNum(Integer maxTabPageNum) {
        this.maxTabPageNum = maxTabPageNum;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
