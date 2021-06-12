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
public class ElvisExprSample {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("tom");
        user.setLastVisit(new Date());
        user.setCredits(100);
        user.setPlaceOfBirth(new PlaceOfBirth("厦门", "中国"));

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        String userName = parser.parseExpression("userName?:'用户名为空'").getValue(context, String.class);
        System.out.println(userName); // tom

        user.setUserName(null);
        userName = parser.parseExpression("userName?:'用户名为空'").getValue(context, String.class);
        System.out.println(userName); // 用户名为空
    }
}
