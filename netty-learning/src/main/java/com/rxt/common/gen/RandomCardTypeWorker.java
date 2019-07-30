package com.rxt.common.gen;

import java.util.*;

/**
 * 随机卡类型生成器
 */
public class RandomCardTypeWorker {
    private static Map<String, Integer> typesFreq = new HashMap<>();
    private static List<String> cards = new ArrayList<>();

    public static void init() {
        typesFreq.put("单程票", 3);
        typesFreq.put("学生票", 4);
        typesFreq.put("羊城通", 40);
        typesFreq.put("二维码", 40);
        typesFreq.put("老年卡", 4);
        typesFreq.put("优待卡", 4);
        typesFreq.put("一日票", 3);
        typesFreq.put("三日票", 2);
    }

    public static void expands() {
        if (typesFreq.size() < 1 || cards.size() < 1) {
            init();
        }

        Set<String> types = typesFreq.keySet();
        for (String s : types) {
            for (int i = 0; i < typesFreq.get(s); i++) {
                cards.add(s);
            }
        }
    }

    public static String genCardType() {
        if (cards.size() < 1) {
            expands();
        }
        return cards.get(new Random().nextInt(100));
    }

    public static void controller(){
        Map<String, Integer> maps = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String type = genCardType();
            if (maps.get(type) != null){
                maps.put(type, maps.get(type) + 1);
            }else{
                maps.put(type, 1);
            }
        }
        System.out.println(maps);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            controller();
        }
    }

}
