package com.denglitong.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/20
 */
@Entity
@Table(name = "t_topic")
public class Topic implements Serializable {

    private static final long serialVersionUID = -2800150605316047794L;

    @Id
    @Column(name = "topic_id")
    private Integer topicId;

    @Column(name = "forum_id")
    private Integer forumId;

    @Column(name = "topic_title")
    private String topicTitle;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "topic_time")
    private Date topicTime;

    @Column(name = "topic_views")
    private Integer topicViews;

    @Column(name = "topic_replies")
    private Integer topicReplies;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Date topicTime) {
        this.topicTime = topicTime;
    }

    public Integer getTopicViews() {
        return topicViews;
    }

    public void setTopicViews(Integer topicViews) {
        this.topicViews = topicViews;
    }

    public Integer getTopicReplies() {
        return topicReplies;
    }

    public void setTopicReplies(Integer topicReplies) {
        this.topicReplies = topicReplies;
    }
}
