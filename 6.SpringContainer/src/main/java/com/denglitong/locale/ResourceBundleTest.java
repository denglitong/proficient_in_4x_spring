package com.denglitong.locale;

import com.sun.tools.doclets.internal.toolkit.util.MessageRetriever;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ResourceBoundle用来加载及访问本地化资源文件，
 * 免去了手动通过 File 访问资源文件的笨拙
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class ResourceBundleTest {

    public static void main(String[] args) {
        ResourceBundle rb1 = ResourceBundle.getBundle("resource", Locale.CHINA);
        ResourceBundle rb2 = ResourceBundle.getBundle("resource", Locale.US);
        // same as rb1
        ResourceBundle rb3 = ResourceBundle.getBundle("resource");
        // not found, will be default, which same as rb1
        ResourceBundle rb4 = ResourceBundle.getBundle("resource", Locale.FRANCE);
        System.out.println(rb1.getString("greeting.common"));
        System.out.println(rb2.getString("greeting.common"));
        System.out.println(rb3.getString("greeting.common"));
        System.out.println(rb4.getString("greeting.common"));

        Object[] params = {"John", new GregorianCalendar().getTime()};
        String str1 = new MessageFormat(rb2.getString("greeting.common1"), Locale.US)
                .format(params);
        String str2 = new MessageFormat(rb2.getString("greeting.morning1"), Locale.US)
                .format(params);
        String str3 = new MessageFormat(rb2.getString("greeting.afternoon1"), Locale.US)
                .format(params);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        String[] beans = {"classpath:com/denglitong/locale/beans.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(beans);

        MessageSource ms = ctx.getBean("myResource", MessageSource.class);
        Object[] params1 = {"John", new GregorianCalendar().getTime()};

        System.out.println(ms.getMessage("greeting.common1", params1, Locale.US));
        System.out.println(ms.getMessage("greeting.morning1", params1, Locale.US));
        System.out.println(ms.getMessage("greeting.afternoon1", params1, Locale.US));
        System.out.println(ms.getMessage("greeting.common1", params1, Locale.CHINA));
        System.out.println(ms.getMessage("greeting.morning1", params1, Locale.CHINA));
        System.out.println(ms.getMessage("greeting.afternoon1", params1, Locale.CHINA));

        String[] contextBeans = {"classpath:com/denglitong/locale/context-locale.xml"};
        // ApplicationContext 也实现了 MessageResource 接口，国际化是提供容器级的支持的
        ApplicationContext context = new ClassPathXmlApplicationContext(contextBeans);
        System.out.println(context.getMessage("greeting.common1", params1, Locale.US));
        System.out.println(context.getMessage("greeting.common1", params1, Locale.CHINA));
    }
}
