package com.denglitong.dao.mybatis;

import com.denglitong.domain.Forum;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
@Repository
public class ForumMybatisTemplateDao extends BaseDao {

    private SqlSessionTemplate sqlSessionTemplate;

    private ForumMybatisDao forumMybatisDao;

    @PostConstruct
    public void init() {
        // 通过 sqlSessionTemplate 获取接口类和 mybatis xml 的映射，
        // 如果不一致将会抛出异常，避免了手动通过字符串指定映射项
        forumMybatisDao = sqlSessionTemplate.getMapper(ForumMybatisDao.class);
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Forum getForum(int forumId) {
        return forumMybatisDao.getForum(forumId);
    }

    public static void main(String[] args) {
        String configPath = "classpath:appContextMybatis.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        ForumMybatisTemplateDao dao = context.getBean(ForumMybatisTemplateDao.class);

        System.out.println(dao.getForum(10));
    }
}
