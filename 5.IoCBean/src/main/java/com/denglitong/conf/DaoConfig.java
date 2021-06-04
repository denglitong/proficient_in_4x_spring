package com.denglitong.conf;

import com.denglitong.annotation.LogDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Configuration
public class DaoConfig {

    @Bean
    public LogDAO logDAO() {
        return new LogDAO();
    }
}
