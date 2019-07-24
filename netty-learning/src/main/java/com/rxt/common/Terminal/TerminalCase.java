package com.rxt.common.Terminal;

import java.util.Random;
import java.util.UUID;

/**
 * 模拟默认多个终端
 */
public class TerminalCase extends Thread{

    private String name;

    public TerminalCase() {
        this.name = Thread.currentThread().getName();
    }

    public TerminalCase(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true){
            System.out.println(this.name + " ==> Data Package: " + UUID.randomUUID().toString());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
