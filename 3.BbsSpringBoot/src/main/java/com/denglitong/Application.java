package com.denglitong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/2
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

    /**
     * 在非 Web 应用中，直接启动 Application 类运行没有问题，
     * 但在 Web 应用中，上述方法直接运行应用，在访问有视图的页面（如 JSP 时），会一直报 404 错误。
     * 运行 Web 应用一定要基于 spring-boot-maven-plugin 插件命令来运行；
     * 另外使用maven 插件运行的命令行如下：
     * mvn org.springframework.boot:spring-boot-maven-plugin:2.5.0:run
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 使用 @Bean 添加自定义事务管理器方法，此时 SpringBoot 会加载自定义的事物管理器，
     * 不会重新实例化其他事务管理器。
     * 如果项目需要分布式事务支持，可导入 spring-boot-starter-jta-atomikos 或
     * spring-boot-starter-jta-bitronix
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 在 Spring Boot 环境中配置 MVC：
     * 继承 SpringBootServletInitializer，重写 configure() 方法
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
