package com.denglitong.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 使用注解定义代替 hibernate 的 dao xml 配置
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/20
 */
@Entity
@Table(name = "t_forum")
public class Forum implements Serializable {

    private static final long serialVersionUID = -4096655829658813358L;

    @Id
    @Column(name = "forum_id")
    private Integer forumId;

    @Column(name = "forum_name")
    private String forumName;

    @Column(name = "forum_desc")
    private String forumDesc;

    public Forum() {}

    public Forum(String forumName, String forumDesc) {
        this.forumName = forumName;
        this.forumDesc = forumDesc;
    }

    public Forum(Integer forumId, String forumName, String forumDesc) {
        this.forumId = forumId;
        this.forumName = forumName;
        this.forumDesc = forumDesc;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getForumDesc() {
        return forumDesc;
    }

    public void setForumDesc(String forumDesc) {
        this.forumDesc = forumDesc;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "forumId=" + forumId +
                ", forumName='" + forumName + '\'' +
                ", forumDesc='" + forumDesc + '\'' +
                '}';
    }
}
