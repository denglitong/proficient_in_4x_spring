package com.denglitong.conf;

import com.denglitong.annotation.LogDAO;
import com.denglitong.annotation.LogonService;
import com.denglitong.annotation.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Configuration
// 使用 @Import 将多个依赖的配置类组装到一个配置类中
@Import({UserDAO.class, LogDAO.class, DaoConfig.class, LogonService.class})
public class ServiceConfigImport {

    private DaoConfig daoConfig;

    /**
     * 使用 \@Configuration 注解的类自动包含了 \@Component
     * 所以可以用来自动注入
     *
     * @param daoConfig
     */
    @Autowired
    public void setDaoConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean(name = "logonService2")
    public LogonService logonService() {
        LogonService logonService = new LogonService();
        logonService.setLogDAO(daoConfig.logDAO());
        return logonService;
    }

    public DaoConfig getDaoConfig() {
        return daoConfig;
    }
}
