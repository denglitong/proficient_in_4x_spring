package com.denglitong.domain;

import java.io.Serializable;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/20
 */
public class Forum implements Serializable {

    private static final long serialVersionUID = -4096655829658813358L;

    private Integer forumId;

    private String forumName;

    private String forumDesc;

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
