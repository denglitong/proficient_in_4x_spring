package com.denglitong.dao;

import com.denglitong.domain.Forum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/21
 */
public class ForumDaoTest {

    public static void main(String[] args) {
        String configPath = "classpath:application.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");

        ForumDao forumDao = (ForumDao)ctx.getBean("forumDao");
        forumDao.setJdbcTemplate(jdbcTemplate);

        Forum forum = new Forum();
        forum.setForumName("帖子 3");
        forum.setForumDesc("hello, world");
        // forumDao.addForum(forum);
        // forumDao.addForum1(forum);

        // System.out.println(forum);
        // forumDao.addForumWithGenerateId(forum);
        // System.out.println(forum);

        // List<Forum> forums = forumDao.getForumsByIdRange(10, 11);
        // System.out.println(forums);
        //
        // System.out.println("forum num: " + forumDao.getForumNum());
        //
        // System.out.println(forumDao.getReplyRate(1));
        //
        // System.out.println("user<1> topic num: " + forumDao.getUserTopicNum(1));
        // System.out.println("user<1> topic num: " + forumDao.getUserTopicNum2(1));

        // forumDao.addForumByNamedParams(forum);
        forumDao.getForumByIdRange3(Arrays.asList(10, 11, 13))
                .forEach(System.out::println);
    }
}
