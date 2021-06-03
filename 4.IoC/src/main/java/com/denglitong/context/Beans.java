package com.denglitong.context;

import com.denglitong.reflect.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AnnotationConfigApplicationContext 专门用来装载基于注解的类
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
@Configuration // 表示是一个配置信息提供类
public class Beans {

    // 定义一个 Bean
    @Bean(name = "redCar")
    public Car buildCar() {
        Car car = new Car();
        car.setBrand("红旗 CA72");
        car.setColor("红色");
        car.setMaxSpeed(200);
        return car;
    }
}
