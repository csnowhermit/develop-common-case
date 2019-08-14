package com.rxt.common.autoFlow2.graph;

import com.rxt.common.autoFlow2.ContextParam;
import com.rxt.common.autoFlow2.RPoint;

import java.util.*;

/**
 * 准备所有点位
 * 数据来源：passRouteMap
 */
public class PreData {
    public static void main(String[] args) {
        Map<String, List<RPoint>> passRouteMap = ContextParam.getPassRouteMap();
        Set<String> pointSet = new HashSet<>();

        for (String key : passRouteMap.keySet()) {
//            System.out.print(key + " --> ");
            List<RPoint> rPointList = passRouteMap.get(key);
            for(RPoint rPoint:rPointList){
//                System.out.println(rPoint.getX() + "," + rPoint.getY());
                pointSet.add(rPoint.getX() + "," + rPoint.getY());
            }
        }

        System.out.println(pointSet.size());

        for (int i = 0; i < 100; i++) {
            System.out.println(String.valueOf(System.currentTimeMillis() + new Random().nextLong()).hashCode()%20);
        }

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());

        for (int i = 0; i < 100; i++) {
            System.out.println(i + " --> " + System.nanoTime());
        }

    }
}
