package com.denglitong.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/1
 */
public class LoginLog implements Serializable {

    private static final long serialVersionUID = -2380384221591057146L;

    private Integer loginLogId;
    private Integer userId;
    private String ip;
    private Date loginDate;

    public Integer getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Integer loginLogId) {
        this.loginLogId = loginLogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "loginLogId=" + loginLogId +
                ", userId=" + userId +
                ", ip='" + ip + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}
