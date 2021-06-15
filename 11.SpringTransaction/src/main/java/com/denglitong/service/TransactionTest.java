package com.denglitong.service;

import com.denglitong.domain.Forum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
public class TransactionTest {

    public static void main(String[] args) {
        String configPath = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        // ForumService forumService = context.getBean(ForumService.class);
        // Forum forum = new Forum();
        // forum.setForumName("帖子1");
        // forum.setForumDesc("今天是周二");
        // forumService.addForum(forum);

        String configPath1 = "classpath:applicationContext-tx.xml";
        ApplicationContext context1 = new ClassPathXmlApplicationContext(configPath1);

        // ForumService forumService1 = context1.getBean(ForumService.class);
        // Forum forum1 = new Forum();
        // forum1.setForumName("帖子2");
        // forum1.setForumDesc("今天是周三");
        // forumService1.addForum(forum1);

        BbtForumService bbtForumService = context1.getBean(BbtForumService.class);
        Forum forum2 = bbtForumService.getForum(17);
        System.out.println(ToStringBuilder.reflectionToString(forum2));
    }
}
