package com.denglitong.conf;

import com.denglitong.annotation.LogonService;
import com.denglitong.annotation.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JavaConfig 是 Spring 的一个子项目，旨在通过 Java 类的方式提供 Bean 定义信息
 * \@Configuration + \@Bean
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Configuration
public class AppConf {

    /**
     * \@Bean 的 类型由返回值决定，名称默认和方法名相同
     *
     * @return
     */
    @Bean(name = "userDAO")
    public UserDAO userDAO() {
        return new UserDAO();
    }

    @Bean
    public LogonService logonService() {
        LogonService logonService = new LogonService();
        // 将 userDAO 注入
        logonService.setUserDAO(userDAO());
        return logonService;
    }
}
