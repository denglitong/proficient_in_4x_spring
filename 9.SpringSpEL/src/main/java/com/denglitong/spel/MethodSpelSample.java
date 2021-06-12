package com.denglitong.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class MethodSpelSample {

    public static void main(String[] args) {
        User user = new User();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        String substring = parser.parseExpression("'String SpEL'.substring(7)")
                .getValue(String.class);
        System.out.println(substring);

        Integer index = parser.parseExpression("'Spring SpEL'.indexOf('SpEL')")
                .getValue(Integer.class);
        System.out.println(index);

        // 调用实例方法
        boolean isCorrect = parser.parseExpression("validatePassword('123456')").getValue(context, Boolean.class);
        System.out.println(isCorrect);
        // 调用私有方法，报错
        // isCorrect = parser.parseExpression("validatePassword2('123456')").getValue(context, Boolean.class);
        // System.out.println(isCorrect);
        // 调用静态方法
        isCorrect = parser.parseExpression("validatePassword3('123456')").getValue(context, Boolean.class);
        System.out.println(isCorrect);

        parser.parseExpression("addInterests('Js','C','Java')").getValue(context);
        List list = (List)parser.parseExpression("getInterestsList()").getValue(context);
        System.out.println(list);
    }
}
