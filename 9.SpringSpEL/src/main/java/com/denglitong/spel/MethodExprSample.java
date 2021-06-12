package com.denglitong.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class MethodExprSample {

    public static void main(String[] args) {
        User user = new User();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        boolean falseVal = parser.parseExpression("true && false").getValue(Boolean.class);
        System.out.println(falseVal);

        String expression = "isVipMember('tom') && isVipMember('jony')";
        boolean trueVal = parser.parseExpression(expression).getValue(context, Boolean.class);
        System.out.println(trueVal);

        trueVal = parser.parseExpression("true or false").getValue(Boolean.class);
        System.out.println(trueVal);

        falseVal = parser.parseExpression("!true").getValue(Boolean.class);
        System.out.println(falseVal);
    }
}
