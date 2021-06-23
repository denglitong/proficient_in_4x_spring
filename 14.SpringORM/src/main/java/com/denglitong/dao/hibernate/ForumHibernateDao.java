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
public class ForumHibernateDao extends BaseDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addForum(final Forum forum) {
        getHibernateTemplate().save(forum);
    }

    public void addForum2(final Forum forum) {
        // 使用 hibernate 原生 API
        sessionFactory.getCurrentSession().save(forum);
    }

    public void updateForm(final Forum forum) {
        getHibernateTemplate().update(forum);
    }

    public void updateForum2(final Forum forum) {
        sessionFactory.getCurrentSession().update(forum);
    }

    public Forum getForum(int forumId) {
        return getHibernateTemplate().get(Forum.class, forumId);
    }

    // 使用 HQL 查询
    public List<Forum> findForumByName(String forumName) {
        return (List<Forum>)getHibernateTemplate().find(
                "from Forum where forumName like ?", forumName + "%");
    }

    public long getForumNum() {
        Object obj = getHibernateTemplate()
                .iterate("select count (forumId) from Forum ")
                .next();
        return (long)obj;
    }

    public long getForumNum2() {
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                return (Long)session.createQuery("select count(forumId) from Forum")
                        .list()
                        .iterator()
                        .next();
            }
        });
    }

    public static void main(String[] args) {
        String configPath = "classpath:appContextHibernate.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);

        ForumHibernateDao dao = (ForumHibernateDao)context.getBean("forumHibernateDao");
        System.out.println(dao.getForum(10));
        System.out.println(dao.getForumNum());
        System.out.println(dao.getForumNum2());

        Forum forum = new Forum();
        forum.setForumId(14);
        forum.setForumName("帖子 4");
        forum.setForumDesc("nice to meet you");
        dao.addForum(forum);

        forum.setForumId(15);
        forum.setForumName("帖子 5");
        forum.setForumDesc("you are beautiful");
        dao.addForum2(forum);
    }
}
