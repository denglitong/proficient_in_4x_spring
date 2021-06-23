package com.denglitong.dao.hibernate;

import com.denglitong.domain.Post;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
@Repository
public class PostHibernateDao extends BaseDao {

    public void addPost(final Post post) {
        getHibernateTemplate().save(post);
    }

    public Post getPost(final int postId) {
        return getHibernateTemplate().get(Post.class, postId);
    }
}
