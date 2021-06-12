package com.denglitong.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/12
 */
public class IfThenElseExprSample {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("tom");
        user.setLastVisit(new Date());
        user.setCredits(100);
        user.setPlaceOfBirth(new PlaceOfBirth("厦门", "中国"));

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        // 能自动适配属性字段的大小写
        String expression = "UserName == 'tom' ? credits+10 : credits";
        Integer credits = parser.parseExpression(expression).getValue(context, Integer.class);
        System.out.println(credits);
    }
}
