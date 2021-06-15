package com.denglitong.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
public class Topic implements Serializable {

    private static final long serialVersionUID = -6847485320372050885L;

    private int topicId;

    private String topicTitle;

    private Date topicTime;

    private Post post;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Date getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Date topicTime) {
        this.topicTime = topicTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
