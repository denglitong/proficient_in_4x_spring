package com.denglitong.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class PropertyExprSample {

    public static User getUser() {
        User user = new User("tom");
        user.setLastVisit(new Date());
        user.setCredits(100);
        user.setPlaceOfBirth(new PlaceOfBirth("厦门", "中国"));
        user.setInterestsArray(new String[]{"PingPong", "basketball"});
        user.setInterestsList(Arrays.asList("PingPong", "basketball"));
        user.setInterestsMap(Stream.of(new String[][]{{"tom", "Pingpong"}, {"jony", "basketball"}})
                .collect(Collectors.toMap(data -> data[0], data -> data[1])));
        return user;
    }

    public static void main(String[] args) {
        User user = getUser();

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        // 对象解析需要在取值时传递上下文对象
        String userName = (String)parser.parseExpression("userName").getValue(context);
        int credits = (int)parser.parseExpression("credits").getValue(context);
        String city = (String)parser.parseExpression("placeOfBirth.city").getValue(context);

        System.out.println(userName);
        System.out.println(credits);
        System.out.println(city);
    }
}
