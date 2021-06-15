package com.denglitong.service;

import com.denglitong.dao.ForumDao;
import com.denglitong.dao.PostDao;
import com.denglitong.dao.TopicDao;
import com.denglitong.domain.Forum;
import com.denglitong.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * \@Transactional 可用于接口定义、接口方法、类定义和类 public 方法上
 * 建议在业务实现类上使用\@Transactional 注解，因为注解不能被继承，
 * 所以业务接口中标注的注解不会被业务实现类继承
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
@Service
@Transactional
public class BbtForumService {

    private ForumDao forumDao;

    private TopicDao topicDao;

    private PostDao postDao;

    public void addTopic(Topic topic) {
        topicDao.addTopic(topic);
        postDao.addPost(topic.getPost());
    }

    @Transactional(readOnly = true)
    public Forum getForum(int forumId) {
        return forumDao.getForum(forumId);
    }

    @Transactional(transactionManager = "txManager1")
    public void updateForum(Forum forum) {
        forumDao.updateForum(forum);
    }

    public int getForumNum() {
        return forumDao.getForumNum();
    }

    @Autowired
    public void setForumDao(ForumDao forumDao) {
        this.forumDao = forumDao;
    }

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}
