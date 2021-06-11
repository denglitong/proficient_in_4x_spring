package com.denglitong.fun;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.core.Ordered;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
@Aspect
public class EnableSellerAspect implements Ordered {

    @DeclareParents(value = "com.denglitong.fun.NaiveWaiter",
            defaultImpl = SmartSeller.class)
    public Seller seller;

    @Override
    public int getOrder() {
        return 1;
    }
}
