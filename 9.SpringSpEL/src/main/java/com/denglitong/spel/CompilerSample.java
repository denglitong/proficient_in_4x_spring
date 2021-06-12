package com.denglitong.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class CompilerSample {

    public static void main(String[] args) {
        // 创建解析配置
        // SpelCompilerMode.MIXED 混合编译，达到阈值后开启编译（100 次）
        SpelParserConfiguration configuration = new SpelParserConfiguration(
                SpelCompilerMode.IMMEDIATE, // 立即启用编译
                CompilerSample.class.getClassLoader());

        // 创建解析器
        SpelExpressionParser parser = new SpelExpressionParser(configuration);

        User user = new User();
        EvaluationContext context = new StandardEvaluationContext(user);

        String expression = "isVipMember('tom') && isVipMember('jony')";
        SpelExpression spelExpression = parser.parseRaw(expression);

        System.out.println(spelExpression.getValue(context)); // 第一次不会立即启用编译
        System.out.println(spelExpression.getValue(context)); // 第二次执行表达式才会启用编译
    }
}
