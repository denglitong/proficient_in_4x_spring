package com.denglitong.mixlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/18
 */
@Controller
public class MixLayerUserService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/logon.do")
    @Transactional
    public String logon(String userName, String password) {
        if (isRightUser(userName, password)) {
            String sql = "UPDATE t_user SET credits = credits + ? WHERE user_name = ?";
            jdbcTemplate.update(sql, 20, userName);
            // 返回渲染 success.jsp 模板
            return "success";
        }
        // 返回渲染 fail.jsp 模板
        return "fail";
    }

    private boolean isRightUser(String userName, String password) {
        // TODO
        return true;
    }
}
