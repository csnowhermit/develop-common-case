package com.rxt.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 随机生成常见汉字
 */
public class GeneCharUtil {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.print(getRandomChar() + ", ");
        }
    }

    /**
     * 随机生成常见汉字
     *
     * @return
     */
    public static char getRandomChar() {
        String str = "";
        int highPos;
        int lowPos;

        Random random = new Random();

        highPos = 176 + Math.abs(random.nextInt(39));
        lowPos = 161 + Math.abs(random.nextInt(93));

        byte[] b = new byte[2];
        b[0] = Integer.valueOf(highPos).byteValue();
        b[1] = Integer.valueOf(lowPos).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str.charAt(0);
    }
}
