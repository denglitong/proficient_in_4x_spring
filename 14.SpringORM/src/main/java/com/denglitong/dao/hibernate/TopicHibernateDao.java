package com.denglitong.dao.hibernate;

import com.denglitong.domain.Topic;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
@Repository
public class TopicHibernateDao extends BaseDao {

    public void addTopic(final Topic topic) {
        getHibernateTemplate().save(topic);
    }

    public Topic getTopic(final int topicId) {
        return getHibernateTemplate().get(Topic.class, topicId);
    }
}
