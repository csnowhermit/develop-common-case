package com.rxt.common.autoFlow2;

import java.util.*;

/**
 * 各个重要的点
 */
public class ContextParam {
    /**
     * <进出类型，路径Region列表>
     */
    private static Map<String, List<RPoint>> passRouteMap = new HashMap<>();

    public static Map<String, List<RPoint>> getPassRouteMap() {
        return passRouteMap;
    }

    /**
     * 类启动时候执行静态块，加载预置化操作
     */
    static {
        if (passRouteMap.size() == 0) {
            loadPassRoute();
        }
    }

    /**
     * D口，电梯进
     */
    private static void loadD_in_elv() {
        List<RPoint> list = new ArrayList<>();
        //D口区域
        list.add(new RPoint(PointTag.TAG_X, new Point(633, 1697)));
        list.add(new RPoint(PointTag.TAG_X, new Point(633, 1405)));
        list.add(new RPoint(PointTag.TAG_X, new Point(633, 1115)));
        list.add(new RPoint(PointTag.TAG_X, new Point(633, 905)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1057, 889)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1449, 889)));

        //AD通道区域
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 889)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 1143)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 1461)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 1779)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 2105)));

        //HA区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1769, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2159, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2549, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4499, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4889, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5279, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5669, 2621)));    //HC11左侧，5669

        passRouteMap.put("D进电", list);
    }

    /**
     * D口步梯进
     */
    private static void loadD_in_sta(){
        List<RPoint> list = new ArrayList<>();
        //D口区域
        list.add(new RPoint(PointTag.TAG_X, new Point(953, 1697)));
        list.add(new RPoint(PointTag.TAG_X, new Point(953, 1405)));
        list.add(new RPoint(PointTag.TAG_X, new Point(953, 1115)));
        list.add(new RPoint(PointTag.TAG_X, new Point(953, 905)));
        list.add(new RPoint(PointTag.TAG_X, new Point(953, 889)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1065, 889)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1449, 889)));

        //AD通道区域
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 889)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 1143)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 1461)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 1779)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1689, 2105)));

        //HA区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1769, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2159, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2549, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4499, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4889, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5279, 2621)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5669, 2621)));    //HC11左侧，5669

        passRouteMap.put("D进步", list);
    }

    /**
     * 加载预置化客流路径
     */
    private static void loadPassRoute() {
        loadD_in_elv();    //D口电梯进
        loadD_in_sta();    //D口步梯进
    }

    public static Point randomPoint(RPoint rPoint) {
        Point point = new Point();

        if (rPoint.getFlag() == PointTag.TAG_X) {
            point.setX(rPoint.getX() + new Random().nextInt(10));
            point.setY(rPoint.getY());
        } else {
            point.setX(rPoint.getX());
            point.setY(rPoint.getY() + new Random().nextInt(10));
        }

        return point;
    }


    public static void print() {
        for (String key : passRouteMap.keySet()) {
            System.out.println(key + " --> " + passRouteMap.get(key));
        }
    }

    public static void main(String[] args) {
        print();

        List<RPoint> rPointList = passRouteMap.get("D进步");
        for (RPoint rPoint : rPointList) {
            System.out.println(ContextParam.randomPoint(rPoint));
        }
    }


}
