package com.rxt.common.autoFlow2;

import java.util.*;

/**
 * 站图上所有点位
 */
public class ContextParam {
    /**
     * <进出类型，路径Region列表>
     */
    private static Map<String, List<RPoint>> passRouteMap = new HashMap<>();

    /**
     * AD通道进口闸机列表
     * <闸机编号，闸机进出位置像素点List>
     */
    private static Map<String, List<RPoint>> gateBrakeMap_AD_in = new HashMap<>();

    /**
     * B通道进口闸机列表
     * <闸机编号，闸机进出位置像素点List>
     */
    private static Map<String, List<RPoint>> gateBrakeMap_BC_in = new HashMap<>();

    /**
     * AD口出闸机列表
     * <闸机编号，闸机进出位置像素点List>
     */
    private static Map<String, List<RPoint>> gateBrakeMap_AD_out = new HashMap<>();

    /**
     * <线段编号，点位序列>
     */
    private static Map<RouteSegMark, List<RPoint>> routeSectionMap = new HashMap<>();

    public static Map<RouteSegMark, List<RPoint>> getRouteSectionMap() {
        return routeSectionMap;
    }

    public static Map<String, List<RPoint>> getGateBrakeMap_AD_in() {
        return gateBrakeMap_AD_in;
    }

    public static Map<String, List<RPoint>> getGateBrakeMap_BC_in() {
        return gateBrakeMap_BC_in;
    }

    public static Map<String, List<RPoint>> getGateBrakeMap_AD_out() {
        return gateBrakeMap_AD_out;
    }

    public static Map<String, List<RPoint>> getPassRouteMap() {
        return passRouteMap;
    }

    /**
     * 类启动时候执行静态块，加载预置化操作
     */
    static {
        // 1.加载闸机列表
        if (gateBrakeMap_AD_in.size() == 0) {
            loadGateBrakeMap_AD_in();
        }
        if (gateBrakeMap_BC_in.size() == 0) {
            loadGateBrakeMap_BC_in();
        }
        if (gateBrakeMap_AD_out.size() == 0) {
            loadGateBrakeMap_AD_out();
        }

        // 2.加载线段路径
        if (routeSectionMap.size() == 0) {
            loadRouteSection();
        }

        // 3.加载预置化路线
        if (passRouteMap.size() == 0) {
            loadPassRoute();
        }
    }

    /**
     * 加载线段路径
     */
    private static void loadRouteSection() {
        // 1.D口进，进AD闸机（HA区域）
        List<RPoint> list1 = new ArrayList<>();
        list1.add(new RPoint(PointTag.TAG_Y, new Point(1769, 2105)));
        list1.add(new RPoint(PointTag.TAG_Y, new Point(2159, 2105)));
        list1.add(new RPoint(PointTag.TAG_Y, new Point(2549, 2105)));
        list1.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list1.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list1.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));
        routeSectionMap.put(RouteSegMark.D_IN_HA, list1);

        // 2.AD口进，过闸机后进步梯
        List<RPoint> list2 = new ArrayList<>();
        list2.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2621)));
        list2.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2621)));
        list2.add(new RPoint(PointTag.TAG_X, new Point(4481, 2513)));
        list2.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2513)));
        list2.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2513)));
        routeSectionMap.put(RouteSegMark.AD_IN_STA, list2);

        //3.AD口进：过闸机后进电梯
        List<RPoint> list3 = new ArrayList<>();
        list3.add(new RPoint(PointTag.TAG_Y, new Point(3593, 2533)));
        list3.add(new RPoint(PointTag.TAG_Y, new Point(3409, 2533)));
        list3.add(new RPoint(PointTag.TAG_Y, new Point(3249, 2533)));
        list3.add(new RPoint(PointTag.TAG_Y, new Point(3145, 2533)));
        list3.add(new RPoint(PointTag.TAG_Y, new Point(3009, 2533)));
        routeSectionMap.put(RouteSegMark.AD_IN_ELV, list3);

        // 4.D口进，到AD通道
        List<RPoint> list4 = new ArrayList<>();
        list4.add(new RPoint(PointTag.TAG_X, new Point(1689, 889)));
        list4.add(new RPoint(PointTag.TAG_X, new Point(1689, 1143)));
        list4.add(new RPoint(PointTag.TAG_X, new Point(1689, 1461)));
        list4.add(new RPoint(PointTag.TAG_X, new Point(1689, 1779)));
        list4.add(new RPoint(PointTag.TAG_X, new Point(1689, 2105)));
        routeSectionMap.put(RouteSegMark.D_IN_AD, list4);

        // 5.D口电梯进
        List<RPoint> list5 = new ArrayList<>();
        list5.add(new RPoint(PointTag.TAG_X, new Point(633, 1697)));
        list5.add(new RPoint(PointTag.TAG_X, new Point(633, 1405)));
        list5.add(new RPoint(PointTag.TAG_X, new Point(633, 1115)));
        list5.add(new RPoint(PointTag.TAG_X, new Point(633, 905)));
        list5.add(new RPoint(PointTag.TAG_Y, new Point(1057, 889)));
        list5.add(new RPoint(PointTag.TAG_Y, new Point(1449, 889)));
        routeSectionMap.put(RouteSegMark.D_IN_ELV, list5);

        // 6.D口步梯进
        List<RPoint> list6 = new ArrayList<>();
        list6.add(new RPoint(PointTag.TAG_X, new Point(993, 1697)));
        list6.add(new RPoint(PointTag.TAG_X, new Point(993, 1405)));
        list6.add(new RPoint(PointTag.TAG_X, new Point(993, 1115)));
        list6.add(new RPoint(PointTag.TAG_X, new Point(993, 905)));
        list6.add(new RPoint(PointTag.TAG_X, new Point(993, 889)));
        list6.add(new RPoint(PointTag.TAG_Y, new Point(1065, 889)));
        list6.add(new RPoint(PointTag.TAG_Y, new Point(1449, 889)));
        routeSectionMap.put(RouteSegMark.D_IN_STA, list6);

        // 7.A口电梯进
        List<RPoint> list7 = new ArrayList<>();
        list7.add(new RPoint(PointTag.TAG_X, new Point(721, 3041)));
        list7.add(new RPoint(PointTag.TAG_X, new Point(721, 3617)));
        list7.add(new RPoint(PointTag.TAG_X, new Point(721, 3889)));
        list7.add(new RPoint(PointTag.TAG_X, new Point(721, 4065)));
        list7.add(new RPoint(PointTag.TAG_X, new Point(721, 4169)));
        list7.add(new RPoint(PointTag.TAG_Y, new Point(993, 4169)));
        list7.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4169)));
        list7.add(new RPoint(PointTag.TAG_Y, new Point(1289, 4169)));
        list7.add(new RPoint(PointTag.TAG_Y, new Point(1433, 4169)));
        list7.add(new RPoint(PointTag.TAG_Y, new Point(1553, 4169)));
        list7.add(new RPoint(PointTag.TAG_X, new Point(1553, 3899)));
        routeSectionMap.put(RouteSegMark.A_IN_ELV, list7);

        // 8.A口步梯进
        List<RPoint> list8 = new ArrayList<>();
        list8.add(new RPoint(PointTag.TAG_X, new Point(721, 3041)));
        list8.add(new RPoint(PointTag.TAG_X, new Point(721, 3617)));
        list8.add(new RPoint(PointTag.TAG_X, new Point(721, 3889)));
        list8.add(new RPoint(PointTag.TAG_X, new Point(721, 4065)));
        list8.add(new RPoint(PointTag.TAG_Y, new Point(841, 4065)));
        list8.add(new RPoint(PointTag.TAG_Y, new Point(993, 4065)));
        list8.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4065)));
        list8.add(new RPoint(PointTag.TAG_Y, new Point(1289, 4065)));
        list8.add(new RPoint(PointTag.TAG_Y, new Point(1433, 4065)));
        list8.add(new RPoint(PointTag.TAG_Y, new Point(1553, 4065)));
        list8.add(new RPoint(PointTag.TAG_X, new Point(1553, 3899)));
        routeSectionMap.put(RouteSegMark.A_IN_STA, list8);

        // 9.A口进：AD通道
        List<RPoint> list9 = new ArrayList<>();
        list9.add(new RPoint(PointTag.TAG_X, new Point(1553, 3601)));
        list9.add(new RPoint(PointTag.TAG_X, new Point(1553, 3345)));
        list9.add(new RPoint(PointTag.TAG_X, new Point(1553, 3097)));
        list9.add(new RPoint(PointTag.TAG_X, new Point(1553, 2865)));
        routeSectionMap.put(RouteSegMark.A_IN_AD, list9);

        // 10.A口进，到站厅
        List<RPoint> list10 = new ArrayList<>();
        list10.add(new RPoint(PointTag.TAG_X, new Point(1617, 2641)));    //准备往站厅拐
        list10.add(new RPoint(PointTag.TAG_Y, new Point(1737, 2529)));
        list10.add(new RPoint(PointTag.TAG_Y, new Point(1913, 2529)));

        list10.add(new RPoint(PointTag.TAG_X, new Point(2081, 2361)));
        list10.add(new RPoint(PointTag.TAG_X, new Point(2193, 2145)));
        list10.add(new RPoint(PointTag.TAG_X, new Point(2549, 2105)));

        //HA区
        list10.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list10.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list10.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));
        routeSectionMap.put(RouteSegMark.A_IN_HA, list10);

        // 11.电梯出，到出口闸机口
        List<RPoint> list11 = new ArrayList<>();
        //HC区
        list11.add(new RPoint(PointTag.TAG_Y, new Point(3000, 2673)));
        list11.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2673)));
        list11.add(new RPoint(PointTag.TAG_Y, new Point(3577, 2673)));

        //HD区
        list11.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list11.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list11.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list11.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list11.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        routeSectionMap.put(RouteSegMark.ELV_OUT_GATE, list11);

        // 12.步梯出，到出口闸机口
        List<RPoint> list12 = new ArrayList<>();
        //HC区
        list12.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2481)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2481)));
        list12.add(new RPoint(PointTag.TAG_X, new Point(4481, 2481)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2641)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2641)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(3929, 2641)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(3777, 2641)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(3617, 2641)));

        //HD区
        list12.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list12.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list12.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list12.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        routeSectionMap.put(RouteSegMark.STA_OUT_GATE, list12);

        // 13.出口：出口闸机到A口电梯
        List<RPoint> list13 = new ArrayList<>();
        //HD区：HD1
        list13.add(new RPoint(PointTag.TAG_Y, new Point(1961, 3057)));
        list13.add(new RPoint(PointTag.TAG_Y, new Point(1737, 3057)));

        //AD区：AD7-AD11
        list13.add(new RPoint(PointTag.TAG_X, new Point(1737, 3225)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(1737, 3353)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(1737, 3585)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(1737, 3833)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(1737, 4001)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(1737, 4129)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(1737, 4281)));

        //A口区域
        list13.add(new RPoint(PointTag.TAG_Y, new Point(1609, 4281)));
        list13.add(new RPoint(PointTag.TAG_Y, new Point(1449, 4281)));
        list13.add(new RPoint(PointTag.TAG_Y, new Point(1305, 4281)));
        list13.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4281)));
        list13.add(new RPoint(PointTag.TAG_Y, new Point(993, 4281)));
        list13.add(new RPoint(PointTag.TAG_Y, new Point(721, 4281)));
        list13.add(new RPoint(PointTag.TAG_Y, new Point(513, 4281)));

        list13.add(new RPoint(PointTag.TAG_X, new Point(513, 4081)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(513, 3905)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(513, 3753)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(513, 3577)));
        list13.add(new RPoint(PointTag.TAG_X, new Point(513, 3393)));
        routeSectionMap.put(RouteSegMark.GATE_OUT_A_ELV, list13);

        // 14.出口闸机到AD1区域
        List<RPoint> list14 = new ArrayList<>();
        //HC区:HC1
        list14.add(new RPoint(PointTag.TAG_X, new Point(1993, 2585)));

        //HB区：HB1
        list14.add(new RPoint(PointTag.TAG_X, new Point(1993, 2337)));
        list14.add(new RPoint(PointTag.TAG_Y, new Point(1817, 2337)));
        list14.add(new RPoint(PointTag.TAG_Y, new Point(1633, 2337)));
        list14.add(new RPoint(PointTag.TAG_Y, new Point(1537, 2337)));

        //AD区：AD5-AD1
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 2337)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 2089)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 1833)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 1657)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 1573)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 1393)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 1225)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 1073)));
        list14.add(new RPoint(PointTag.TAG_X, new Point(1537, 913)));
        routeSectionMap.put(RouteSegMark.GATE_OUT_AD, list14);

        // 15.AD顶部到D口电梯
        List<RPoint> list15 = new ArrayList<>();
        //D口区域
        list15.add(new RPoint(PointTag.TAG_Y, new Point(1537, 913)));
        list15.add(new RPoint(PointTag.TAG_Y, new Point(1409, 913)));
        list15.add(new RPoint(PointTag.TAG_Y, new Point(1217, 913)));
        list15.add(new RPoint(PointTag.TAG_Y, new Point(1025, 913)));
        list15.add(new RPoint(PointTag.TAG_Y, new Point(801, 913)));

        list15.add(new RPoint(PointTag.TAG_X, new Point(801, 1065)));
        list15.add(new RPoint(PointTag.TAG_X, new Point(801, 1201)));
        list15.add(new RPoint(PointTag.TAG_X, new Point(801, 1329)));
        list15.add(new RPoint(PointTag.TAG_X, new Point(801, 1433)));
        list15.add(new RPoint(PointTag.TAG_X, new Point(801, 1593)));
        list15.add(new RPoint(PointTag.TAG_X, new Point(801, 1705)));
        routeSectionMap.put(RouteSegMark.AD_OUT_D_ELV, list15);

        // 16.AD顶部到D口步梯
        List<RPoint> list16 = new ArrayList<>();
        //D口区域
        list16.add(new RPoint(PointTag.TAG_Y, new Point(1537, 913)));
        list16.add(new RPoint(PointTag.TAG_Y, new Point(1409, 913)));
        list16.add(new RPoint(PointTag.TAG_Y, new Point(1217, 913)));
        list16.add(new RPoint(PointTag.TAG_Y, new Point(1025, 913)));
        list16.add(new RPoint(PointTag.TAG_Y, new Point(929, 913)));

        list16.add(new RPoint(PointTag.TAG_X, new Point(929, 1065)));
        list16.add(new RPoint(PointTag.TAG_X, new Point(929, 1201)));
        list16.add(new RPoint(PointTag.TAG_X, new Point(929, 1329)));
        list16.add(new RPoint(PointTag.TAG_X, new Point(929, 1433)));
        list16.add(new RPoint(PointTag.TAG_X, new Point(929, 1593)));
        list16.add(new RPoint(PointTag.TAG_X, new Point(929, 1705)));
        routeSectionMap.put(RouteSegMark.AD_OUT_D_STA, list16);
    }

    /**
     * 加载预置化AD进口闸机列表
     */
    private static void loadGateBrakeMap_AD_in() {

        List<RPoint> ad1List = new ArrayList<>();
        ad1List.add(new RPoint(PointTag.NONE, new Point(3659, 2290)));    //进闸机处
        ad1List.add(new RPoint(PointTag.NONE, new Point(3659, 2496)));    //处闸机处
        gateBrakeMap_AD_in.put("ad1_in", ad1List);

        //第一个和第二个之间像素宽度为90，可能是宽闸机
        List<RPoint> ad2List = new ArrayList<>();
        ad2List.add(new RPoint(PointTag.NONE, new Point(3749, 2290)));    //进闸机处
        ad2List.add(new RPoint(PointTag.NONE, new Point(3749, 2496)));    //处闸机处
        gateBrakeMap_AD_in.put("ad2_in", ad2List);

        //第三个闸机：之后为窄闸机
        List<RPoint> ad3List = new ArrayList<>();
        ad3List.add(new RPoint(PointTag.NONE, new Point(3829, 2290)));    //进闸机处
        ad3List.add(new RPoint(PointTag.NONE, new Point(3829, 2496)));    //处闸机处
        gateBrakeMap_AD_in.put("ad3_in", ad3List);

        //第四个闸机
        List<RPoint> ad4List = new ArrayList<>();
        ad4List.add(new RPoint(PointTag.NONE, new Point(3909, 2290)));    //进闸机处
        ad4List.add(new RPoint(PointTag.NONE, new Point(3909, 2496)));    //处闸机处
        gateBrakeMap_AD_in.put("ad4_in", ad4List);

        //第五个闸机
        List<RPoint> ad5List = new ArrayList<>();
        ad5List.add(new RPoint(PointTag.NONE, new Point(3989, 2290)));    //进闸机处
        ad5List.add(new RPoint(PointTag.NONE, new Point(3989, 2496)));    //处闸机处
        gateBrakeMap_AD_in.put("ad5_in", ad5List);
    }

    /**
     * 加载预置化BC进口闸机列表
     */
    private static void loadGateBrakeMap_BC_in() {

        List<RPoint> bc1List = new ArrayList<>();
        bc1List.add(new RPoint(PointTag.NONE, new Point(4912, 2290)));    //进闸机处
        bc1List.add(new RPoint(PointTag.NONE, new Point(4912, 2496)));    //处闸机处
        gateBrakeMap_BC_in.put("bc1_in", bc1List);

        //第一个和第二个之间像素宽度为90，可能是宽闸机
        List<RPoint> bc2List = new ArrayList<>();
        bc2List.add(new RPoint(PointTag.NONE, new Point(5002, 2290)));    //进闸机处
        bc2List.add(new RPoint(PointTag.NONE, new Point(5002, 2496)));    //处闸机处
        gateBrakeMap_BC_in.put("bc2_in", bc2List);

        //第三个闸机
        List<RPoint> bc3List = new ArrayList<>();
        bc3List.add(new RPoint(PointTag.NONE, new Point(5082, 2290)));    //进闸机处
        bc3List.add(new RPoint(PointTag.NONE, new Point(5082, 2496)));    //处闸机处
        gateBrakeMap_BC_in.put("bc3_in", bc3List);
    }

    /**
     * 加载预置化AD出口闸机列表
     */
    private static void loadGateBrakeMap_AD_out() {
        List<RPoint> ad1List = new ArrayList<>();
        ad1List.add(new RPoint(PointTag.NONE, new Point(2499, 2929)));    //进闸机处
        ad1List.add(new RPoint(PointTag.NONE, new Point(2229, 2929)));    //处闸机处
        gateBrakeMap_AD_out.put("ad1_out", ad1List);

        List<RPoint> ad2List = new ArrayList<>();
        ad2List.add(new RPoint(PointTag.NONE, new Point(2499, 2989)));    //进闸机处
        ad2List.add(new RPoint(PointTag.NONE, new Point(2229, 2989)));    //处闸机处
        gateBrakeMap_AD_out.put("ad2_out", ad2List);

        List<RPoint> ad3List = new ArrayList<>();
        ad3List.add(new RPoint(PointTag.NONE, new Point(2499, 3050)));    //进闸机处
        ad3List.add(new RPoint(PointTag.NONE, new Point(2229, 3050)));    //处闸机处
        gateBrakeMap_AD_out.put("ad3_out", ad3List);

        List<RPoint> ad4List = new ArrayList<>();
        ad4List.add(new RPoint(PointTag.NONE, new Point(2499, 3113)));    //进闸机处
        ad4List.add(new RPoint(PointTag.NONE, new Point(2229, 3113)));    //处闸机处
        gateBrakeMap_AD_out.put("ad4_out", ad4List);

        List<RPoint> ad5List = new ArrayList<>();
        ad5List.add(new RPoint(PointTag.NONE, new Point(2499, 3179)));    //进闸机处
        ad5List.add(new RPoint(PointTag.NONE, new Point(2229, 3179)));    //处闸机处
        gateBrakeMap_AD_out.put("ad5_out", ad5List);
    }

    /**
     * D口，电梯进：进步梯
     */
    private static void loadD_in_elv2sta() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_ELV));   //D口区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_AD));   //AD通道区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_HA));    //HA区域、进闸机前一步：到闸机口了

            //HB区域：闸机处
//        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //进之后
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_STA));  //HC区域，中部楼梯下

            passRouteMap.put("D进电步" + brake_id, list);
        }
    }

    /**
     * D口，电梯进：进电梯
     */
    private static void loadD_in_elv2elv() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_ELV));    //D口区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_AD));    //AD通道区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_HA));    //HA区域、进闸机之前的点

            //HB区域
//            list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));   //进闸机后
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            //HC区域
//        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));    //进闸机后向电梯走一步

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_ELV));

            passRouteMap.put("D进电电" + brake_id, list);
        }
    }

    /**
     * D口，步梯进：进电梯
     */
    private static void loadD_in_sta2elv() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_STA));   //D口区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_AD));    //AD通道区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_HA));    //HA区域、进闸机前的点

            //HB区域
//        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //进闸机后
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            //HC区域
            //通过闸机后走了一步：往电梯方向。随机过闸机的话该点应取消，否则会出现右走又左走的情况（不合常理）
//        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_ELV));

            passRouteMap.put("D进步电" + brake_id, list);
        }
    }

    /**
     * D口，步梯进：进步梯
     */
    private static void loadD_in_sta2sta() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_STA));    //D口区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_AD));    //AD通道区域
            list.addAll(routeSectionMap.get(RouteSegMark.D_IN_HA));    //HA区域

            //HB区域
//        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_STA));    //HC区域、中部楼梯下

            passRouteMap.put("D进步步" + brake_id, list);
        }
    }

    /**
     * A口电梯进：进电梯
     */
    private static void loadA_in_elv2elv() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_ELV));   //电梯进站
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_AD));   //AD通道
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_HA));    //站厅

            //HB区域
//        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });


            //HC区域
//        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_ELV));    //电梯下

            passRouteMap.put("A进电电" + brake_id, list);
        }
    }

    /**
     * A口电梯进：进步梯
     */
    private static void loadA_in_elv2sta() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_ELV));    // 步梯进站
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_AD));    //AD通道
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_HA));    //站厅

            //HB区域
//        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //通过闸机之后
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_STA));    //HC区域、中部楼梯下

            passRouteMap.put("A进电步" + brake_id, list);
        }
    }

    /**
     * A口步梯进：进电梯
     */
    private static void loadA_in_sta2elv() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_STA));    //步梯进站
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_AD));    //AD通道
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_HA));    //站厅

            //HB区域
//        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //通过闸机之后
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            //HC区域
//        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));    //通过后走一步，电梯方向

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_ELV));    //电梯下

            passRouteMap.put("A进步电" + brake_id, list);
        }
    }

    /**
     * A口步梯进：进步梯
     */
    private static void loadA_in_sta2sta() {
        for (String brake_id : gateBrakeMap_AD_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_STA));    //步梯进站
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_AD));    //AD通道
            list.addAll(routeSectionMap.get(RouteSegMark.A_IN_HA));    //站厅

            //HB区域
//        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //刚好通过闸机
            gateBrakeMap_AD_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.AD_IN_STA));    //HC区域、中部楼梯下

            passRouteMap.put("A进步步" + brake_id, list);
        }
    }

    /**
     * B口步梯进3：进电梯3
     */
    private static void loadB_in_sta3() {
        for (String brake_id : gateBrakeMap_BC_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            //站外铁马阵
            list.add(new RPoint(PointTag.TAG_X, new Point(4313, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4417, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4513, 4081)));

            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4617)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(4625, 4617)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4729, 4617)));

            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4081)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(4849, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4961, 4081)));

            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4617)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(5057, 4617)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5145, 4617)));

            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4081)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(5257, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5369, 4081)));

            list.add(new RPoint(PointTag.TAG_X, new Point(5369, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5369, 4433)));

            list.add(new RPoint(PointTag.TAG_X, new Point(5489, 4305)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5689, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5761, 4665)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5881, 4777)));   //到步梯口了

            //B口楼梯
            list.add(new RPoint(PointTag.TAG_Y, new Point(5985, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6105, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6201, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6313, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6457, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6617, 4777)));

            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4705)));
            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4617)));
            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4489)));
            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4385)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(6713, 4385)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6833, 4385)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6953, 4385)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(7089, 4385)));

            //BC通道
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 4185)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 4033)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3873)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3737)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3561)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3401)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3157)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2921)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2761)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2573)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2361)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2121)));


            //HA区
            list.add(new RPoint(PointTag.TAG_Y, new Point(6729, 2121)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6369, 2121)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5857, 2121)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5473, 2121)));    //进闸机前一步

            //HB区
//        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2265)));    //闸机口
//        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2513)));    //刚通过闸机口
            gateBrakeMap_BC_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            //HC区
            list.add(new RPoint(PointTag.TAG_Y, new Point(5145, 2513)));    //进入3号电梯
            list.add(new RPoint(PointTag.TAG_Y, new Point(5305, 2513)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5449, 2513)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5561, 2513)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5705, 2513)));

            passRouteMap.put("B进步电3" + brake_id, list);
        }
    }

    /**
     * B口步梯进4：进电梯4
     */
    private static void loadB_in_sta4() {
        for (String brake_id : gateBrakeMap_BC_in.keySet()) {
            List<RPoint> list = new ArrayList<>();

            //站外铁马阵
            list.add(new RPoint(PointTag.TAG_X, new Point(4313, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4417, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4513, 4081)));

            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4513, 4617)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(4625, 4617)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4729, 4617)));

            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4729, 4081)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(4849, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(4961, 4081)));

            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(4961, 4617)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(5057, 4617)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5145, 4617)));

            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4433)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5145, 4081)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(5257, 4081)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5369, 4081)));

            list.add(new RPoint(PointTag.TAG_X, new Point(5369, 4265)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5369, 4433)));

            list.add(new RPoint(PointTag.TAG_X, new Point(5489, 4305)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5689, 4521)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5761, 4665)));
            list.add(new RPoint(PointTag.TAG_X, new Point(5881, 4777)));   //到步梯口了

            //B口楼梯
            list.add(new RPoint(PointTag.TAG_Y, new Point(5985, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6105, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6201, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6313, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6457, 4777)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6617, 4777)));

            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4705)));
            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4617)));
            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4489)));
            list.add(new RPoint(PointTag.TAG_X, new Point(6617, 4385)));

            list.add(new RPoint(PointTag.TAG_Y, new Point(6713, 4385)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6833, 4385)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6953, 4385)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(7089, 4385)));

            //BC通道
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 4185)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 4033)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3873)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3737)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3561)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3401)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 3157)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2921)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2761)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2573)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2361)));
            list.add(new RPoint(PointTag.TAG_X, new Point(7033, 2121)));


            //HA区
            list.add(new RPoint(PointTag.TAG_Y, new Point(6729, 2121)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(6369, 2121)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5857, 2121)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5473, 2121)));    //进闸机前一步

            //HB区
//        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2265)));    //到闸机口
//        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2513)));    //刚过闸机
            gateBrakeMap_BC_in.get(brake_id).forEach(item -> {
                list.add(item);
            });

            //HC区
//        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2689)));    //往下走一步，准备进4号电梯

            list.add(new RPoint(PointTag.TAG_Y, new Point(5145, 2689)));    //已进入4号电梯
            list.add(new RPoint(PointTag.TAG_Y, new Point(5305, 2689)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5449, 2689)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5561, 2689)));
            list.add(new RPoint(PointTag.TAG_Y, new Point(5705, 2689)));

            passRouteMap.put("B进步电4" + brake_id, list);
        }
    }

    /**
     * D口电梯出：出到电梯
     */
    private static void loadD_out_elv2elv() {
        for (String brake_id : gateBrakeMap_AD_out.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.ELV_OUT_GATE));

            // 准备随机过闸机
//        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
//        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //出闸机了、这一步之后可往A口出
//        list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));    // 该点去掉
            gateBrakeMap_AD_out.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.GATE_OUT_AD));    //出口闸机到AD顶部
            list.addAll(routeSectionMap.get(RouteSegMark.AD_OUT_D_ELV));    //D口电梯

            passRouteMap.put("D出电电" + brake_id, list);
        }
    }

    /**
     * D口电梯出：出到步梯
     */
    private static void loadD_out_elv2sta() {
        for (String brake_id : gateBrakeMap_AD_out.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.ELV_OUT_GATE));

            //准备随机过闸机
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出
//            list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));
            gateBrakeMap_AD_out.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.GATE_OUT_AD));    //出口处闸机到AD顶部
            list.addAll(routeSectionMap.get(RouteSegMark.AD_OUT_D_STA));   //AD顶部到D口步梯

            passRouteMap.put("D出电步" + brake_id, list);
        }
    }

    /**
     * D口步梯出：出到电梯
     */
    private static void loadD_out_sta2elv() {
        for (String brake_id : gateBrakeMap_AD_out.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.STA_OUT_GATE));

            // 准备随机过闸机
//        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
//        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出
//        list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));
            gateBrakeMap_AD_out.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.GATE_OUT_AD));    //出口闸机到AD顶部
            list.addAll(routeSectionMap.get(RouteSegMark.AD_OUT_D_ELV));    //AD顶部到D口电梯

            passRouteMap.put("D出步电" + brake_id, list);
        }
    }

    /**
     * D口步梯出：出到步梯
     */
    private static void loadD_out_sta2sta() {
        for (String brake_id : gateBrakeMap_AD_out.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.STA_OUT_GATE));

            //准备随机过闸机
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出
//            list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));
            gateBrakeMap_AD_out.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.GATE_OUT_AD));
            list.addAll(routeSectionMap.get(RouteSegMark.AD_OUT_D_STA));

            passRouteMap.put("D出步步" + brake_id, list);
        }
    }

    /**
     * A口电梯出：出到电梯
     */
    private static void loadA_out_elv2elv() {
        for (String brake_id : gateBrakeMap_AD_out.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.ELV_OUT_GATE));

            //准备随机过闸机
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出
            gateBrakeMap_AD_out.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.GATE_OUT_A_ELV));

            passRouteMap.put("A出电电" + brake_id, list);
        }
    }

    /**
     * A口步梯出：出到电梯
     */
    private static void loadA_out_sta2elv() {
        for (String brake_id : gateBrakeMap_AD_out.keySet()) {
            List<RPoint> list = new ArrayList<>();

            list.addAll(routeSectionMap.get(RouteSegMark.STA_OUT_GATE));

            //准备随机过闸机
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
//            list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出
            gateBrakeMap_AD_out.get(brake_id).forEach(item -> {
                list.add(item);
            });

            list.addAll(routeSectionMap.get(RouteSegMark.GATE_OUT_A_ELV));

            passRouteMap.put("A出步电" + brake_id, list);
        }
    }

    /**
     * 加载预置化客流路径
     */
    private static void loadPassRoute() {
        //进口
        loadD_in_elv2elv();    //D口电梯进：电梯
        loadD_in_elv2sta();    //D口电梯进：步梯

        loadD_in_sta2elv();    //D口步梯进：电梯
        loadD_in_sta2sta();    //D口步梯进：步梯

        loadA_in_elv2elv();    //A口电梯进：电梯
        loadA_in_elv2sta();    //A口电梯进：步梯

        loadA_in_sta2elv();    //A口步梯进：电梯
        loadA_in_sta2sta();    //A口步梯进，步梯

        loadB_in_sta3();    //B口步梯进：电梯3
        loadB_in_sta4();    //B口步梯进：电梯4

        //出口
        loadD_out_elv2elv();   //D口电梯出：出到电梯
        loadD_out_elv2sta();   //D口电梯出：出到步梯

        loadD_out_sta2elv();   //D口步梯出：出到电梯
        loadD_out_sta2sta();   //D口步梯出：出到步梯

        loadA_out_elv2elv();   //A口电梯出：出到电梯
        loadA_out_sta2elv();   //A口步梯出：出到电梯
    }

    public static Point randomPoint(RPoint rPoint) {
        Point point = new Point();

        if (rPoint.getFlag() == PointTag.NONE) {
            point.setX(rPoint.getX());
            point.setY(rPoint.getY());
        } else if (rPoint.getFlag() == PointTag.TAG_X) {
//            point.setX(rPoint.getX() + new Random().nextInt(10));
            point.setX(rPoint.getX() +
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 30 * rPoint.getRandMulti()));
            point.setY(rPoint.getY());
        } else if (rPoint.getFlag() == PointTag.TAG_Y) {
            point.setX(rPoint.getX());
//            point.setY(rPoint.getY() + new Random().nextInt(10));
            point.setY(rPoint.getY() +
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 30 * rPoint.getRandMulti()));
        } else {
            point.setX(rPoint.getX() +
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 30 * rPoint.getRandMulti()));
            point.setY(rPoint.getY() +
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 30 * rPoint.getRandMulti()));
        }

        //再确定位置：站台/站厅
        point.setZ(rPoint.getZ());

        return point;
    }


    public static void print() {
        for (String key : passRouteMap.keySet()) {
            System.out.println(key + " --> " + passRouteMap.get(key));
        }

        gateBrakeMap_AD_in.forEach((k, v) -> {
            System.out.println(k + " --> " + v);
        });
        gateBrakeMap_BC_in.forEach((k, v) -> {
            System.out.println(k + " --> " + v);
        });
        gateBrakeMap_AD_out.forEach((k, v) -> {
            System.out.println(k + " --> " + v);
        });
    }

    public static void main(String[] args) {
        print();
    }

}

/**
 * 线路段标识
 */
enum RouteSegMark {
    // 进站路段
    A_IN_STA,    //A口步梯进
    D_IN_HA,     //D口进，进AD闸机（HA区域）
    A_IN_HA,     //A口进，到站厅（AD闸机之前）
    A_IN_ELV,    //A口电梯进
    AD_IN_ELV,   //AD口进，过闸机后进电梯
    D_IN_STA,    //D口步梯进
    D_IN_ELV,    //D口电梯进
    AD_IN_STA,   //AD口进，过闸机后进步梯
    D_IN_AD,     //D口进，到AD通道
    A_IN_AD,     //A口进，到AD通道

    // 出站路段
    ELV_OUT_GATE,     //电梯出，到出口闸机口
    STA_OUT_GATE,     //步梯出，到出口闸机口
    GATE_OUT_A_ELV,   //出口闸机到A口电梯
    GATE_OUT_AD,      //出口闸机到AD顶部
    AD_OUT_D_ELV,     //AD顶部到D口电梯
    AD_OUT_D_STA      //AD顶部到D口步梯
}