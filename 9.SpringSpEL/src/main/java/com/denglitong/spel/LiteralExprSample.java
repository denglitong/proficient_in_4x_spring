package com.denglitong.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class LiteralExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        String helloWorld = (String)parser.parseExpression("\"Hello World\"").getValue();

        double doubleNumber = (double)parser.parseExpression("6.0221415E+23").getValue();

        int maxValue = (int)parser.parseExpression("0x7FFFFFFF").getValue();

        boolean trueValue = (boolean)parser.parseExpression("true").getValue();

        Object nullValue = parser.parseExpression("null").getValue();

        System.out.println(helloWorld);
        System.out.println(doubleNumber);
        System.out.println(maxValue);
        System.out.println(trueValue);
        System.out.println(nullValue);
    }
}
