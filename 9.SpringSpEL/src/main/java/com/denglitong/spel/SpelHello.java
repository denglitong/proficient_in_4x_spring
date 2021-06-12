package com.denglitong.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class SpelHello {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'hello'+'World'");
        String message = (String)exp.getValue();
        System.out.println(message);

        Simple simple = new Simple();
        simple.booleanList.add(true);
        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
        parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);
    }
}
