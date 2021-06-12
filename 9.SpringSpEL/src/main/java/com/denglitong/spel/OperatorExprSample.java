package com.denglitong.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * ==
 * !=
 * <
 * <=
 * >
 * >=
 * 正则匹配 match expr
 * instanceof
 * <p>
 * 加减乘除 + - * /
 * 取模 %
 * 指数幂 ^
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class OperatorExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        boolean trueVal = parser.parseExpression("2 == 2").getValue(boolean.class);
        System.out.println(trueVal);

        boolean falseVal = parser.parseExpression(" 2 < -5.0").getValue(boolean.class);
        System.out.println(falseVal);

        trueVal = parser.parseExpression("\"black\" < \"block\"").getValue(boolean.class);
        System.out.println(trueVal);

        falseVal = parser.parseExpression("'xyz' instanceof T(int)").getValue(boolean.class);
        System.out.println(falseVal);

        trueVal = parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(boolean.class);
        System.out.println(trueVal);

        falseVal = parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(boolean.class);
        System.out.println(falseVal);

        int two = parser.parseExpression("1 + 1").getValue(int.class);
        System.out.println(two);

        String testString = parser.parseExpression("\"test\" + ' ' + \"string\"").getValue(String.class);
        System.out.println(testString);

        int four = parser.parseExpression("1 - -3").getValue(int.class);
        System.out.println(four);

        double d = parser.parseExpression("1000 - 1e4").getValue(double.class);
        System.out.println(d);

        int six = parser.parseExpression("-2 * -3").getValue(int.class);
        System.out.println(six);

        double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(double.class);
        System.out.println(twentyFour);

        int minusTwo = parser.parseExpression("6 / -3").getValue(int.class);
        System.out.println(minusTwo);

        double one = parser.parseExpression("8.0 / 4e0 / 2").getValue(double.class);
        System.out.println(one);

        int three = parser.parseExpression("7 % 4").getValue(int.class);
        System.out.println(three);

        one = parser.parseExpression("8 / 5 % 2").getValue(int.class);
        System.out.println(one);

        int minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(int.class);
        System.out.println(minusTwentyOne);

    }
}
