package com.denglitong.service.mybatis;

import com.denglitong.dao.mybatis.ForumMybatisDao;
import com.denglitong.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
@Service
@Transactional
public class BbtForumService {

    private ForumMybatisDao forumMybatisDao;

    @Autowired
    public void setForumMybatisDao(ForumMybatisDao forumMybatisDao) {
        this.forumMybatisDao = forumMybatisDao;
    }

    public Forum getForum(final int forumId) {
        return forumMybatisDao.getForum(forumId);
    }

    public static void main(String[] args) {
        String configPath = "classpath:appContextMybatis.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        BbtForumService service = context.getBean(BbtForumService.class);

        System.out.println(service.getForum(13));
    }
}
