package com.denglitong.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
@Aspect
public class EnableSellerAspect {

    /**
     * 为 NaiveWaiter 添加 Seller 接口实现，默认实现类为 SmartSeller
     * <p>
     * 使用 Seller 实现一个引介增强，其增强的目标类是 NaiveWaiter，
     * 增强的默认实现是 SmartSeller
     */
    @DeclareParents(value = "com.denglitong.aspectj.NaiveWaiter",
            defaultImpl = SmartSeller.class)
    public Seller seller;
}
