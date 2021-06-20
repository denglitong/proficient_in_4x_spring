package com.denglitong.withouttx.hibernate;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/18
 */
@Service("hibernateService")
public class UserHibernateWithoutTransactionService {

    // 此处注入的 hibernateTemplate 没有配置事务管理器，
    // 使用了 Spring 默认的事务管理器策略（PROPAGATION_REQUIRED,read_only）只读事务
    private HibernateTemplate hibernateTemplate;

    public void addCredits(String userName, int toAdd) {
        User user = hibernateTemplate.get(User.class, userName);
        user.setCredits(user.getCredits() + toAdd);
        // Write operations are not allowed in read-only mode (FlushMode.MANUAL):
        // Turn your Session into FlushMode.COMMIT/AUTO or remove 'readOnly' marker from transaction definition.
        hibernateTemplate.update(user);
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public static void main(String[] args) throws InterruptedException {
        String configPath = "classpath:com/denglitong/withouttx/hibernate/hibernateWithoutTx.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        UserHibernateWithoutTransactionService service =
                (UserHibernateWithoutTransactionService)context.getBean("hibernateService");

        JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");

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
