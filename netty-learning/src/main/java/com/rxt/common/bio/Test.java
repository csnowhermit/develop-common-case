/**
 * FileName: Test
 * Author:   Ren Xiaotian
 * Date:     2018/11/1 21:49
 */

package com.rxt.common.bio;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
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

        //随机生成表达式
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String expression = random.nextInt(10) + ""
                            + op[random.nextInt(4)] + ""
                            + (random.nextInt(10) + 1);
                    Client.send(expression);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
