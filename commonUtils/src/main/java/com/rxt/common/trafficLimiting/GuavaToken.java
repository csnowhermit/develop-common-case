package com.rxt.common.trafficLimiting;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * guava 漏桶和令牌桶的差异
 */
public class GuavaToken {

    private int qps;
    private int countOfReq;
    private RateLimiter rateLimiter;

    public GuavaToken(int qps, int countOfReq) {
        this.qps = qps;
        this.countOfReq = countOfReq;
    }

    /**
     * 初始化令牌桶
     *
     * @return
     */
    public GuavaToken initTokenBucket() {
        rateLimiter = RateLimiter.create(qps);
        return this;
    }

    /**
     * 初始化漏桶
     *
     * @return
     */
    public GuavaToken initLeakyBucket() {
        rateLimiter = RateLimiter.create(qps, 0, TimeUnit.MILLISECONDS);
        return this;
    }

    private void processRequest() {
        System.out.println("RateLimiter: " + rateLimiter.getClass());
        long start = System.currentTimeMillis();
        for (int i = 0; i < countOfReq; i++) {
            rateLimiter.acquire();    //获取令牌
        }

        long end = System.currentTimeMillis() - start;
        System.out.println("处理的请求数量：" + countOfReq + ", " +
                "耗时：" + end + ", " +
                "qps：" + rateLimiter.getRate() + ", " +
                "实际qps：" + Math.ceil(countOfReq / (end / 1000.00)));
    }

    public void doProcessor() throws InterruptedException {
        for (int i = 0; i < 20; i += 5) {
            TimeUnit.SECONDS.sleep(i);
            processRequest();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GuavaToken(50, 100).initTokenBucket().doProcessor();
        new GuavaToken(50, 100).initLeakyBucket().doProcessor();
    }

}
