package com.denglitong.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/12
 */
@Component
public class MyDataSource {

    @Value("#{properties['driverClassName']}")
    private String driverClassName;

    @Value("#{properties['url']}")
    private String url;

    // 使用 SpEL 表达式引用 <bean> 里面的属性值
    @Value("#{properties['userName']}")
    private String userName;

    // <bean> 里面开启 properties-placeholder，可直接使用 ${属性键}
    @Value("${password}")
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
