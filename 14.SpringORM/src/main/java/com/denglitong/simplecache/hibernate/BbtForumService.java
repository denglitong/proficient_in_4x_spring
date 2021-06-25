package com.denglitong.service.hibernate;

import com.denglitong.dao.hibernate.ForumHibernateDao;
import com.denglitong.dao.hibernate.PostHibernateDao;
import com.denglitong.dao.hibernate.TopicHibernateDao;
import com.denglitong.domain.Forum;
import com.denglitong.domain.Post;
import com.denglitong.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
@Service
@Transactional
public class BbtForumService {

    private ForumHibernateDao forumHibernateDao;

    private TopicHibernateDao topicHibernateDao;

    private PostHibernateDao postHibernateDao;

    @Autowired
    public void setForumHibernateDao(ForumHibernateDao forumHibernateDao) {
        this.forumHibernateDao = forumHibernateDao;
    }

    @Autowired
    public void setTopicHibernateDao(TopicHibernateDao topicHibernateDao) {
        this.topicHibernateDao = topicHibernateDao;
    }

    @Autowired
    public void setPostHibernateDao(PostHibernateDao postHibernateDao) {
        this.postHibernateDao = postHibernateDao;
    }

    public void addForum(final Forum forum) {
        forumHibernateDao.addForum(forum);
    }

    public void addTopic(final Topic topic) {
        topicHibernateDao.addTopic(topic);
    }

    public void addPost(final Post post) {
        postHibernateDao.addPost(post);
    }

    public List<Forum> findForumByName(String forumName) {
        return forumHibernateDao.findForumByName(forumName);
    }

    public Post getPost(final int postId) {
        return postHibernateDao.getPost(postId);
    }

    public static void main(String[] args) {
        String configPath = "classpath:appContextHibernate.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        BbtForumService service = context.getBean(BbtForumService.class);

        System.out.println(service.getPost(1));

        service.findForumByName("帖子")
                .forEach(System.out::println);
    }
}
