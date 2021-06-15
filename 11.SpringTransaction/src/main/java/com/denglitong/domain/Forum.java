package com.denglitong.domain;

import java.io.Serializable;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
public class Forum implements Serializable {

    private static final long serialVersionUID = 2145739407714223490L;

    private int forumId;

    private String forumName;

    private String forumDesc;

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
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
}
