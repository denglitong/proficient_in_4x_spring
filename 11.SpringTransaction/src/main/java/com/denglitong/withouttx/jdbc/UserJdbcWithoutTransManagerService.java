package com.denglitong.withouttx.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/18
 */
@Service("userService")
public class UserJdbcWithoutTransManagerService {

    private JdbcTemplate jdbcTemplate;

    public void addCredits(String userName, int toAdd) {
        String sql = "UPDATE t_user SET credits = credits + ? WHERE user_name = ?";
        Object[] params = new Object[]{toAdd, userName};
        jdbcTemplate.update(sql, params);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        String configPath = "classpath:com/denglitong/withouttx/jdbc/jdbcWithoutTx.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        UserJdbcWithoutTransManagerService service = (UserJdbcWithoutTransManagerService)context.getBean("userService");
        JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
        service.setJdbcTemplate(jdbcTemplate);

        BasicDataSource basicDataSource = (BasicDataSource)jdbcTemplate.getDataSource();
        // basicDataSource.setDefaultAutoCommit(false);
        System.out.println("autoCommit:" + basicDataSource.getDefaultAutoCommit());

        String now = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
        String insertSQL = "INSERT INTO t_user(user_name,password,credits,last_visit) " +
                " VALUES ('tom','123456',10,'" + now + "')";
        jdbcTemplate.execute(insertSQL);

        service.addCredits("tom", 20);

        String querySQL = "SELECT credits FROM t_user WHERE user_name = 'tom'";
        int credits = jdbcTemplate.queryForObject(querySQL, int.class);
        System.out.println("credits:" + credits);

        jdbcTemplate.execute("DELETE FROM t_user WHERE user_name = 'tom'");
    }
}
