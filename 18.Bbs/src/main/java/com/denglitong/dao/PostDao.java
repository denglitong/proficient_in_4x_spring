package com.denglitong.dao;

import com.denglitong.domain.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Repository
public class PostDao extends BaseDao<Post> {

    private static final String GET_PAGE_POSTS =
            " from Post where topic.topicId = ? order by createTime desc";
    private static final String DELETE_TOPIC_POSTS =
            " delete from Post where topic.topicId = ?";

    public Page<Post> getPagePosts(int topicId, int pageNo, int pageSize) {
        return pageQuery(GET_PAGE_POSTS, pageNo, pageSize, topicId);
    }

    @Transactional
    public void deleteTopicPosts(int topicId) {
        getHibernateTemplate().bulkUpdate(DELETE_TOPIC_POSTS, topicId);
    }
}
