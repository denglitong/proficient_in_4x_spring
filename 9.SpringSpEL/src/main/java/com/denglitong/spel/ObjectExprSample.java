package com.denglitong.spel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/12
 */
public class ObjectExprSample {

    public static void main(String[] args) {
        User user = PropertyExprSample.getUser();
        System.out.println(user.getUserName());

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        parser.parseExpression("userName").setValue(context, "jony");
        System.out.println(parser.parseExpression("userName").getValue(context));
        System.out.println(user.getUserName());

        parser.parseExpression("userName='tom'").getValue(context);
        System.out.println(parser.parseExpression("userName").getValue(context));
        System.out.println(user.getUserName());

        Class stringClazz = parser.parseExpression("T(java.lang.String)").getValue(Class.class);
        System.out.println(stringClazz);

        boolean trueVal = parser.parseExpression("'hello' instanceof T(java.lang.String)")
                .getValue(boolean.class);
        System.out.println(trueVal);

        trueVal = parser.parseExpression("placeOfBirth instanceof T(com.denglitong.spel.PlaceOfBirth)")
                .getValue(context, boolean.class);
        System.out.println(trueVal);

        // T(全限定类名).静态方法()
        Object randomValue = parser.parseExpression("T(java.lang.Math).random()").getValue();
        System.out.println(randomValue);

        User user1 = parser.parseExpression("new com.denglitong.spel.User('tom')")
                .getValue(User.class);
        System.out.println(user1.getUserName());

        context.setVariable("newUserName", "jony");
        // 访问变量 #variable
        parser.parseExpression("userName=#newUserName").getValue(context);
        System.out.println(user.getUserName());

        List<Integer> credits = new ArrayList<>();
        credits.addAll(Arrays.asList(150, 100, 90, 50, 110, 130, 70));
        context.setVariable("credits", credits);
        // 集合过滤 ?[selectExpression]
        // List -> ?[#this ...]
        // Map -> ?[key ...] ?[value ...]
        List<Integer> creditsGreater100 = (List<Integer>)
                parser.parseExpression("#credits.?[#this>100]").getValue(context);
        System.out.println(creditsGreater100);

        // 取列表过滤的第一个元素 ^[]
        Integer creditFirstGreater100 = parser.parseExpression("#credits.^[#this > 100]")
                .getValue(context, Integer.class);
        System.out.println(creditFirstGreater100);
        // 取列表过滤的最后一个元素 $[]
        Integer creditLastGreater100 = parser.parseExpression("#credits.$[#this > 100]")
                .getValue(context, Integer.class);
        System.out.println(creditLastGreater100);

        Map<String, Integer> creditsMap = new HashMap<>();
        creditsMap.put("Tom", 95);
        creditsMap.put("Jony", 110);
        creditsMap.put("Morin", 105);
        creditsMap.put("Mose", 120);
        creditsMap.put("Morrow", 60);
        context.setVariable("creditsMap", creditsMap);

        // 取Map过滤的第一个元素 ^[]
        Map<String, Integer> creditsGreater100Map = (Map<String, Integer>)
                parser.parseExpression("#creditsMap.?[value > 100]").getValue(context);
        System.out.println(creditsGreater100Map);

        // 取Map过滤的最后一个元素 $[]
        Map<String, Integer> creditsKeyMap = (Map<String, Integer>)
                parser.parseExpression("#creditsMap.?[key == 'Tom' || key == 'Morrow']")
                        .getValue(context);
        System.out.println(creditsKeyMap);

        Map<String, Integer> creditFirstGreater100Map = (Map<String, Integer>)
                parser.parseExpression("#creditsMap.^[value > 100]").getValue(context);
        System.out.println(creditFirstGreater100Map);
        Map<String, Integer> creditLastGreater100Map = (Map<String, Integer>)
                parser.parseExpression("#creditsMap.$[value > 100]").getValue(context);
        System.out.println(creditLastGreater100Map);

        // 集合转换 ![projectionExpression]
        List<Boolean> creditsIsGreater100 = (List<Boolean>)
                parser.parseExpression("#credits.![#this > 100]").getValue(context);
        System.out.println(creditsIsGreater100);

        List<Boolean> creditsMapIsGreater100 = (List<Boolean>)
                parser.parseExpression("#creditsMap.![value > 100]").getValue(context);
        System.out.println(creditsMapIsGreater100);

    }
}
