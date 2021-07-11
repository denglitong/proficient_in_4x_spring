package com.denglitong.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * 通过 @Inheritance 指定了 PO 映射继承关系
 * - InheritanceType.TABLE_PER_CLASS: 每个类一张表
 * - InheritanceType.JOINED: 连接的子类一张表
 * - InheritanceType.SINGLE_TABLE: 每个类层次结构一张表
 * 通过 @DiscriminatorColumn 指定了 PO 映射继承关系 用来辨别该类的辨别符列
 * 通过 @DiscriminatorValue 指定了用来编写该类的值
 *
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Entity
@Table(name = "t_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("1")
public class Post extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "board_id")
    private int boardId;

    // @ManyToOne 定义多对一关系
    // @JoinColumn 定义关联列
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_text")
    private String postText;

    @Column(name = "create_time")
    private Date createTime;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
