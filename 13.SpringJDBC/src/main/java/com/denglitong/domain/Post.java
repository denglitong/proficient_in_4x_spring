package com.denglitong.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/20
 */
public class Post implements Serializable {

    private static final long serialVersionUID = 4172982628584643681L;

    private Integer postId;

    private Integer topicId;

    private Integer forumId;

    private Integer userId;

    private String postText;

    private byte[] postAttach;

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
                ", postAttach=" + Arrays.toString(postAttach) +
                ", postTime=" + postTime +
                '}';
    }
}
