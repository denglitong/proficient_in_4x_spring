package com.denglitong.locale;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class LocaleTest {

    public static void main(String[] args) {
        Locale locale = new Locale("zh", "CN");
        locale = Locale.CHINA;

        Locale locale1 = new Locale("zh");
        locale1 = Locale.CHINESE;

        Locale locale2 = new Locale("CN");

        // 获取本地系统默认的本地化对象
        // 在启动时更改系统默认的本地化配置：
        // java -Duser.language=en -Duser.region=US ...
        Locale locale3 = Locale.getDefault(); // zh_CN
        System.out.println(locale3);

        // 本地化的数字格式
        NumberFormat currFormat = NumberFormat.getCurrencyInstance(locale3);
        double instance = 123456.78; // 货币金额
        System.out.println(currFormat.format(instance)); // 输出本地化的货币金额

        // 本地化日期格式
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
        Date date = new Date();
        System.out.println(dateFormat.format(date)); // 下午7:04

        // 使用本地化的 MessageFormat
        String pattern1 = "{0}，你好！你于{1}在工商银行存入{2}元。";
        String pattern2 = "At {1,time,short} On {1,date,long}，{0} paid {2,number,currency}.";

        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};

        // 使用默认的本地化对象格式化信息
        String msg1 = MessageFormat.format(pattern1, params);
        System.out.println(msg1);

        // 使用指定的本地化对象格式化信息
        MessageFormat messageFormat = new MessageFormat(pattern2, Locale.US);
        String msg2 = messageFormat.format(params);
        System.out.println(msg2);
    }
}
