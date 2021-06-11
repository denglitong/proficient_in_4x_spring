package com.denglitong.aspectj;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/9
 */
public class SmartSeller implements Seller {

    @Override
    public void sell(String goods, String clientName) {
        System.out.println("SmartSeller.sell " + goods + " to " + clientName + "...");
    }
}
