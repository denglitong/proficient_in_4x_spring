package com.denglitong.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/21
 */
public class TopicDaoTest {

    public static void main(String[] args) {
        String configPath = "classpath:application.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        TopicDao topicDao = (TopicDao)ctx.getBean("topicDao");
        topicDao.setJdbcTemplate(jdbcTemplate);

        SqlRowSet rowSet = topicDao.getTopicRowSet(1);
        while (rowSet.next()) {
            int topicId = rowSet.getInt("topic_id");
            String topicTitle = rowSet.getString("topic_title");
            System.out.println("topicId: " + topicId + " , topicTitle: " + topicTitle);
        }
    }
}
