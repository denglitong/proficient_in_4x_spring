package com.denglitong.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Entity
@Table(name = "t_topic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Topic extends BaseDomain {

    /**
     * 精华主题帖子
     */
    public static final int DIGEST_TOPIC = 1;
    /**
     * 普通的主题帖子
     */
    public static final int NOT_DIGEST_TOPIC = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int topicId;

    @Column(name = "topic_title")
    private String topicTitle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "board_id")
    private int boardId;

    @Transient
    private MainPost mainPost = new MainPost();

    @Column(name = "last_post")
    private Date lastPost;

    @Column(name = "create_time")
    private Date crateTime;

    @Column(name = "topic_views")
    private int topicViews;

    @Column(name = "topic_replies")
    private int topicReplies;

    @Column(name = "digest")
    private int digest = NOT_DIGEST_TOPIC;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public MainPost getMainPost() {
        return mainPost;
    }

    public void setMainPost(MainPost mainPost) {
        this.mainPost = mainPost;
    }

    public Date getLastPost() {
        return lastPost;
    }

    public void setLastPost(Date lastPost) {
        this.lastPost = lastPost;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public int getTopicViews() {
        return topicViews;
    }

    public void setTopicViews(int topicViews) {
        this.topicViews = topicViews;
    }

    public int getTopicReplies() {
        return topicReplies;
    }

    public void setTopicReplies(int topicReplies) {
        this.topicReplies = topicReplies;
    }

    public int getDigest() {
        return digest;
    }

    public void setDigest(int digest) {
        this.digest = digest;
    }
}
