package com.denglitong.annotation;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Component
@Lazy
@Order(value = 2)
public class TwoPlugin implements Plugin {
}
