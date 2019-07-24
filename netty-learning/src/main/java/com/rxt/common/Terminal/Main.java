package com.rxt.common.Terminal;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(380);

        int nums = 380;
        for (int i = 0; i < nums; i++) {
            new Thread(() -> {
                new TerminalCase().run();
            }, "Terminal-" + i).start();
            countDownLatch.countDown();
        }

    }
}
