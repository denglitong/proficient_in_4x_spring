package com.denglitong.spel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/12
 */
public class SpringSpelTest {

    public static void main(String[] args) {
        String config = "classpath:com/denglitong/spel/beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        NumberGuess numberGuess = context.getBean("numberGuess", NumberGuess.class);
        System.out.println(ToStringBuilder.reflectionToString(numberGuess));

        ShapeGuess shapeGuess = context.getBean("shapeGuess", ShapeGuess.class);
        System.out.println(ToStringBuilder.reflectionToString(shapeGuess));

        SystemPropertyBean systemProperty = context.getBean("systemPropertyBean", SystemPropertyBean.class);
        System.out.println(ToStringBuilder.reflectionToString(systemProperty));

        MyDataSource myDataSource = context.getBean(MyDataSource.class);
        System.out.println(ToStringBuilder.reflectionToString(myDataSource));
    }
}
