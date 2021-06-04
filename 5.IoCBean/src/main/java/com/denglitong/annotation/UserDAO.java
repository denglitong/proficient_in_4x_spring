package com.denglitong.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 通过注解定义一个 Bean（需求开启 context 命名空间，以定义要扫描注解的类包）
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Component(value = "userDAO")
// @Scope(value = "singleton")
public class UserDAO {
}
