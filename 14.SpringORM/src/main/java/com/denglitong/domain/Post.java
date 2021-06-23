package com.denglitong.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/20
 */
@Entity
@Table(name = "t_post")
public class Post implements Serializable {

    private static final long serialVersionUID = 4172982628584643681L;

    @Id
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "topic_id")
    private Integer topicId;

    @Column(name = "forum_id")
    private Integer forumId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "post_text")
    private String postText;

    @Column(name = "post_attach")
    private byte[] postAttach;

    @Column(name = "post_time")
    private Date postTime;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public byte[] getPostAttach() {
        return postAttach;
    }

    public void setPostAttach(byte[] postAttach) {
        this.postAttach = postAttach;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", topicId=" + topicId +
                ", forumId=" + forumId +
                ", userId=" + userId +
                ", postText='" + postText + '\'' +
                // ", postAttach=" + Arrays.toString(postAttach) +
                ", postTime=" + postTime +
                '}';
    }
}
