package com.denglitong.dao.hibernate;

import com.denglitong.domain.Forum;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
@Repository
@Transactional
public class ForumHibernateDao2 extends BaseDao2<Forum> {

    // CURD操作简化，扩展类只需编写特定的 DAO 方法即可
    // 一个设计良好的 DAO 基类可以大大减少 DAO 层的代码量，提高项目的开发效率
    public long getForumNum() {
        Object obj = getHibernateTemplate()
                .iterate("select count (forumId) from Forum ")
                .next();
        return (long)obj;
    }

    public static void main(String[] args) {
        String configPath = "classpath:appContextHibernate.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        ForumHibernateDao2 dao = (ForumHibernateDao2)context.getBean("forumHibernateDao2");
        System.out.println(dao.get(10));
        System.out.println(dao.getForumNum());

        Forum forum = new Forum();
        forum.setForumId(17);
        forum.setForumName("帖子 6");
        forum.setForumDesc("nice to meet you");
        dao.save(forum);
    }
}
