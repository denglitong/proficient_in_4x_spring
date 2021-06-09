package com.denglitong.advisor;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/8
 */
public class WaiterDelegate {

    private Waiter waiter;

    private Seller seller;

    /**
     * 代理类，Waiter的方法通过此方法发起调用
     * 如果希望由 service() 发起的其他方法都织入增强，就必须使用流程切面来完成
     *
     * @param clientName
     */
    public void service(String clientName) {
        waiter.greetTo(clientName);
        waiter.serveTo(clientName);
        seller.greetTo(clientName);
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
