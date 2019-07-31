package com.rxt.common.gen;

import java.util.*;

/**
 * 随机卡类型生成器
 */
public class RandomCardTypeWorker {
    private static Map<String, Integer> typesFreq = new HashMap<>();
    private static List<String> cards = new ArrayList<>();

    public static void init() {
        typesFreq.put("单程票", 2);
        typesFreq.put("学生票", 3);
        typesFreq.put("羊城通", 47);
        typesFreq.put("二维码", 40);
        typesFreq.put("老年卡", 3);
        typesFreq.put("优待卡", 2);
        typesFreq.put("一日票", 2);
        typesFreq.put("三日票", 1);
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

    /**
     * 随机生成卡类型，返回卡类型的代号
     * 单程票 1
     * 学生票 2
     * 羊城通 3
     * 二维码 4
     * 老年卡 5
     * 优待卡 6
     * 一日票 7
     * 三日票 8
     *
     * @return
     */
    public static String genCardType() {
        if (cards.size() < 1) {
            expands();
        }
        String type = cards.get(new Random().nextInt(100));
        int tag = 0;
        switch (type) {
            case "单程票":
                tag = 1;
                break;
            case "学生票":
                tag = 2;
                break;
            case "羊城通":
                tag = 3;
                break;
            case "二维码":
                tag = 4;
                break;
            case "老年卡":
                tag = 5;
                break;
            case "优待卡":
                tag = 6;
                break;
            case "一日票":
                tag = 7;
                break;
            case "三日票":
                tag = 8;
                break;
            default:
                tag = 1;
        }

        return Integer.toString(tag);
    }

    public static void controller() {
        Map<String, Integer> maps = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String type = genCardType();
            if (maps.get(type) != null) {
                maps.put(type, maps.get(type) + 1);
            } else {
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
