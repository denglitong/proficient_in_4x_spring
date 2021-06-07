package com.denglitong.editor;

import com.denglitong.beanproperty.ApplicationManager;
import com.denglitong.beanproperty.SystemConfig;
import com.denglitong.placeholder.MyDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class BeanFactoryTest {

    public static void main(String[] args) {
        /*ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:com/denglitong/editor/beans.xml");

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);

        Boss boss = beanFactory.getBean("boss", Boss.class);
        System.out.println(boss);*/

        // DefaultListableBeanFactory cannot work, but ApplicationContext can, why?
        // same as the @Autowired ...

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/denglitong/editor/beans.xml");
        Boss boss = ctx.getBean("boss", Boss.class);
        System.out.println(boss);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        System.out.println(dataSource);

        MyDataSource myDataSource = ctx.getBean(MyDataSource.class);
        System.out.println(myDataSource);

        SystemConfig systemConfig = ctx.getBean("systemConfig", SystemConfig.class);
        System.out.println(systemConfig);

        ApplicationManager applicationManager = ctx.getBean("applicationManager", ApplicationManager.class);
        System.out.println(applicationManager);
    }
}
