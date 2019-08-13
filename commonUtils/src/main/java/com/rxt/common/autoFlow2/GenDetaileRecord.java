//package com.rxt.common.autoFlow2;
//
//import java.util.*;
//
///**
// * 造数：客流明细表
// */
//public class GenDetaileRecord implements Runnable{
//
//    Map<String, List<String>> passRouteMap = ContextParam.getPassRouteMap();
//
//    private static List<String> pass_in=Arrays.asList("A进", "B进", "D进");
//    private static List<String> pass_out=Arrays.asList("A出", "D出");
//
//    private String flag;      //区域标识
//    private Long timestamp;   //时间戳
//    private Map<String, List<DetailsRecord>> maps = new HashMap<>();
//
//    @Override
//    public void run() {
//        // 假设，每个人在每一区域走的时间、步数不一样
//        for (int i = 0; i < new Random().nextInt(10) + 5; i++) {
//            Long curr = timestamp + new Random().nextInt(300);
////            ContextParam.randomPoint();
//        }
//    }
//}
