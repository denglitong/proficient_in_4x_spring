package com.denglitong.dao.mybatis;

import com.denglitong.domain.Forum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通过定义接口，来和 mybatis xml 建立映射，避免使用字符串指定映射项
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
public interface ForumMybatisDao {
    void addForum(Forum forum);
    void updateForum(Forum forum);
    Forum getForum(int forumId);
    long getForumNum();
    List<Forum> findForumByName(String forumName);
}

/*
@Repository
public class ForumMybatisDao extends BaseDao {

    public Forum getForum(final int forumId) {
        // 调用 mybatis xml 文件中定义的 namespace 为 com.denglitong.dao.mybatis.ForumMybatisDao
        // 映射项 id 为 getForum 的查询语句，入参为 forumId
        // 直接通过字符串指定映射项的方式容易出错，不推荐
        return getSqlSessionTemplate()
                .selectOne("com.denglitong.dao.mybatis.ForumMybatisDao.getForum", forumId);
    }

    public void addForum(final Forum forum) {
        getSqlSessionTemplate()
                .insert("com.denglitong.dao.mybatis.ForumMybatisDao.addForum", forum);
    }

    public static void main(String[] args) {
        String configPath = "classpath:appContextMybatis.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        ForumMybatisDao dao = context.getBean(ForumMybatisDao.class);

        System.out.println(dao.getForum(10));

        Forum forum = new Forum("帖子 6", "handsome boy");
        dao.addForum(forum);
    }
}
*/
