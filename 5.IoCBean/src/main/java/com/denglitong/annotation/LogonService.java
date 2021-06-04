package com.denglitong.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Service(value = "logonService")
public class LogonService {

    private UserDAO userDAO;

    private LogDAO logDAO;

    /**
     * Autowired 默认按类型（byType）匹配的方式在容器中查找匹配的 Bean
     * 当有且只有一个 Bean 时将其注入;
     * 当容器有一个以上的 Bean 时，使用 Qualifier 注解限定 Bean 的名称;
     * Qualifier 用在方法上时，Spring 将自欧东选择匹配参数类型的 Bean 进行注入；
     * Qualifier 还可以用在方法参数上
     *
     * @param userDAO
     */
    @Autowired
    @Scope("prototype") // 可更改默认的作用域 singleton
    // @Qualifier(value = "userDAO")
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public LogDAO getLogDAO() {
        return logDAO;
    }

    @Autowired
    public void setLogDAO(LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    @Override
    public String toString() {
        return "LogonService{" +
                "userDAO=" + userDAO +
                ", logDAO=" + logDAO +
                '}';
    }
}
