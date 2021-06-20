package com.denglitong.mixdao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/19
 */
@Service("creditsService")
public class CreditsService extends BaseService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCredits(String userName, int toAdd) {
        String sql = "UPDATE t_user SET credits = credits + ? WHERE user_name = ?";
        jdbcTemplate.update(sql, toAdd, userName);

        BasicDataSource basicDataSource = (BasicDataSource)jdbcTemplate.getDataSource();
        System.out.println("[creditsService.addCredits]激活连接数量：" +
                basicDataSource.getNumActive());
    }
}
