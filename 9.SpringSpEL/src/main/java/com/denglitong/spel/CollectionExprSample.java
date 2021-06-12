package com.denglitong.spel;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class CollectionExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        int[] array1 = (int[])parser.parseExpression("new int[]{1,2,3}").getValue();
        System.out.println(Arrays.toString(array1));

        // 不支持多维数组的初始化
        int[][] array2 = (int[][])parser.parseExpression("new int[2][3]").getValue();
        System.out.println(ToStringBuilder.reflectionToString(array2));

        List list = (List)parser.parseExpression("{1,2,3,4}").getValue();
        System.out.println(list);
        List listOfLists = (List)parser.parseExpression("{{1,2,3},{4,5,6}}").getValue();
        System.out.println(listOfLists);

        Map userInfo1 = (Map)parser.parseExpression("{userName:'tom',credits:100}").getValue();
        System.out.println(userInfo1);

        List userInfo2 = (List)parser.parseExpression("{{userName:'tom',credits:100},{userName:'jony',credits:99}}")
                .getValue();
        System.out.println(userInfo2);

        User user = PropertyExprSample.getUser();
        EvaluationContext context = new StandardEvaluationContext(user);

        String interest1 = (String)parser.parseExpression("interestsArray[0]").getValue(context);
        System.out.println(interest1);
        String interest2 = (String)parser.parseExpression("interestsList[1]").getValue(context);
        System.out.println(interest2);
        String interest3 = (String)parser.parseExpression("interestsMap['tom']").getValue(context);
        System.out.println(interest3);
    }
}
