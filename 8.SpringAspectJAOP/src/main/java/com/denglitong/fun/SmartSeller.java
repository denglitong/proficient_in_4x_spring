package com.denglitong.fun;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/10
 */
public class SmartSeller implements Seller {

    @Override
    public int sell(String goods, String clientName) {
        System.out.println("SmartSeller: sell " + goods + " to " + clientName + "...");
        return 100;
    }

    public void checkBill(int billId) {
        if (billId == 1) {
            throw new IllegalArgumentException("illegal argument Exception");
        } else {
            throw new RuntimeException("runtime Exception");
        }
    }
}
