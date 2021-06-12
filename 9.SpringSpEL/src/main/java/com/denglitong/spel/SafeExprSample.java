package com.denglitong.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 安全导航操作符来源于 Groovy 语言，它避免了空指针异常，避免了繁琐的空对象验证
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/12
 */
public class SafeExprSample {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("tom");
        user.setCredits(100);
        user.setPlaceOfBirth(new PlaceOfBirth("厦门", "中国"));

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        String city = parser.parseExpression("placeOfBirth?.city").getValue(context, String.class);
        System.out.println(city);

        user.setPlaceOfBirth(null);
        city = parser.parseExpression("placeOfBirth?.city").getValue(context, String.class);
        System.out.println(city); // null
    }
}
