package com.rxt.common.jvm;

import java.util.ArrayList;
import java.util.List;

public class JVMTest01 {
    public static void main(String[] args) {
        byte[] bytes = new byte[2 * 1024 * 1024];    //每次new 2M的大小
        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(new byte[2 * 1024 * 1024]);
        }



    }
}
