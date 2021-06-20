package com.denglitong.connleak;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/19
 */
@Service("userService")
public class UserService {

    private JdbcTemplate jdbcTemplate;

    @Setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void logon(String userName) {
        // Connection conn = null;
        try {
            // 1.手动获取连接，这个连接不是当前方法事务上下文绑定的连接，
            //   需要手动释放 Connection.close() 不然会造成连接泄漏。
            // 2.当使用 TransactionAwareDataSourceProxy 对数据源进行代理后，
            //   可自动感知事务上下文然后释放连接，此时不会造成造成连接泄漏：
            //   Fetching JDBC Connection from DataSource
            //   Returning JDBC Connection to DataSource
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            // 使用 DataSourceUtils 获取事务上下文的连接
            // conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());

            String sql = "UPDATE t_user SET last_visit = ? WHERE user_name = ?";
            String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            jdbcTemplate.update(sql, now, userName);

            // 模拟程序代码执行时间
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 显式释放连接
            // DataSourceUtils.releaseConnection(conn, jdbcTemplate.getDataSource());
        }
    }
}
