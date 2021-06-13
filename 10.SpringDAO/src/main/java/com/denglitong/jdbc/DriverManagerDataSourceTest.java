package com.denglitong.jdbc;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/13
 */
public class DriverManagerDataSourceTest {

    public static void main(String[] args) throws Throwable {
        // Spring 自带了一个简单的数据源实现类，一般用于测试用
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName("com.mysql.jdbc.Driver");
        driverManager.setUrl("jdbc:mysql://localhost:3306/sampledb");
        driverManager.setUsername("root");
        driverManager.setPassword("123456");
        Connection connection = driverManager.getConnection();

        System.out.println(connection);
    }
}
