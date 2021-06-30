package com.denglitong.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/27
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3411228600613112694L;

    private String userId;

    @Pattern(regexp = "w{4,30}")
    private String userName;

    @Pattern(regexp = "w{6,30}")
    private String password;

    @Length(min = 2, max = 100)
    private String realName;

    // @Past 注解声明时间必须是一个过去时刻
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @DecimalMin("1000.00")
    @DecimalMax("100000.00")
    @NumberFormat(pattern = "#,###.##") // 4,500.00
    private long salary;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
