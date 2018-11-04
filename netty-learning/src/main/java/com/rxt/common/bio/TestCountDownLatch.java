/**
 * FileName: Test
 * Author:   Ren Xiaotian
 * Date:     2018/11/1 21:49
 */

package com.rxt.common.bio;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 并发访问Server
 */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100);    //避免客户端比服务端先启动

        final char[] op = {'+', '-', '*', '/'};

        final Random random = new Random(System.currentTimeMillis());

        int num = 10000;    //并发数
        CountDownLatch countDownLatch = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {
            //随机生成表达式
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String expression = random.nextInt(10) + ""
                            + op[random.nextInt(4)] + ""
                            + (random.nextInt(10) + 1);
                    Client.send(expression);
                }
            }).start();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            countDownLatch.countDown();
        }

        countDownLatch.await();

        System.exit(0);
    }
}
