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

//    private static Map<String, List<RPoint>>

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
     * D口，电梯进：进步梯
     */
    private static void loadD_in_elv2sta() {
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
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));    //进闸机前一步：到闸机口了

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //进之后

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2621)));    //进之后走一步，到步梯口了
        list.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2621)));
        list.add(new RPoint(PointTag.TAG_X, new Point(4481, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2513)));    //中部楼梯下

        passRouteMap.put("D进电步", list);
    }

    /**
     * D口，电梯进：进电梯
     */
    private static void loadD_in_elv2elv() {
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
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));    //进闸机之前的点

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));   //进闸机后

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));    //进闸机后向电梯走一步

        list.add(new RPoint(PointTag.TAG_Y, new Point(3593, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3409, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3249, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3145, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3009, 2533)));


        passRouteMap.put("D进电电", list);
    }

    /**
     * D口，步梯进：进电梯
     */
    private static void loadD_in_sta2elv() {
        List<RPoint> list = new ArrayList<>();
        //D口区域
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 1697)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 1405)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 1115)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 905)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 889)));
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
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));    //进闸机前的点

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //进闸机后

        //HC区域
        //通过闸机后走了一步：往电梯方向。随机过闸机的话该点应取消，否则会出现右走又左走的情况（不合常理）
        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3593, 2533)));    //在最左侧闸机下方，往电梯方向第二步
        list.add(new RPoint(PointTag.TAG_Y, new Point(3409, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3249, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3145, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3009, 2533)));

        passRouteMap.put("D进步电", list);
    }

    /**
     * D口，步梯进：进步梯
     */
    private static void loadD_in_sta2sta() {
        List<RPoint> list = new ArrayList<>();
        //D口区域
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 1697)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 1405)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 1115)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 905)));
        list.add(new RPoint(PointTag.TAG_X, new Point(993, 889)));
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
        list.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2621)));
        list.add(new RPoint(PointTag.TAG_X, new Point(4481, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2513)));    //中部楼梯下

        passRouteMap.put("D进步步", list);
    }

    /**
     * A口电梯进：进电梯
     */
    private static void loadA_in_elv2elv() {
        List<RPoint> list = new ArrayList<>();

        //A口外
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3041)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3617)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3889)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 4065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 4169)));

        //电梯处
        list.add(new RPoint(PointTag.TAG_Y, new Point(993, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1289, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1433, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1553, 4169)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3899)));

        //AD通道
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3601)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3345)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3097)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 2865)));

        list.add(new RPoint(PointTag.TAG_X, new Point(1617, 2641)));    //准备往站厅拐
        list.add(new RPoint(PointTag.TAG_Y, new Point(1737, 2529)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1913, 2529)));

        list.add(new RPoint(PointTag.TAG_X, new Point(2081, 2361)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2193, 2145)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2549, 2105)));

        //HA区
        list.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3593, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3409, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3249, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3145, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3009, 2533)));    //电梯下


        passRouteMap.put("A进电电", list);
    }

    /**
     * A口电梯进：进步梯
     */
    private static void loadA_in_elv2sta() {
        List<RPoint> list = new ArrayList<>();

        //A口外
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3041)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3617)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3889)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 4065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 4169)));

        //电梯处
        list.add(new RPoint(PointTag.TAG_Y, new Point(993, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1289, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1433, 4169)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1553, 4169)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3899)));

        //AD通道
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3601)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3345)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3097)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 2865)));

        list.add(new RPoint(PointTag.TAG_X, new Point(1617, 2641)));    //准备往站厅拐
        list.add(new RPoint(PointTag.TAG_Y, new Point(1737, 2529)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1913, 2529)));

        list.add(new RPoint(PointTag.TAG_X, new Point(2081, 2361)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2193, 2145)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2549, 2105)));

        //HA区
        list.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));    //进闸机之前

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //通过闸机之后

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2621)));    //通过闸机之后走一步，到达步梯口
        list.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2621)));
        list.add(new RPoint(PointTag.TAG_X, new Point(4481, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2513)));    //中部楼梯下


        passRouteMap.put("A进电步", list);
    }


    /**
     * A口步梯进：进电梯
     */
    private static void loadA_in_sta2elv() {
        List<RPoint> list = new ArrayList<>();

        //A口外
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3041)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3617)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3889)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 4065)));

        //步梯处
        list.add(new RPoint(PointTag.TAG_Y, new Point(841, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(993, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1289, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1433, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1553, 4065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3899)));

        //AD通道
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3601)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3345)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3097)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 2865)));

        list.add(new RPoint(PointTag.TAG_X, new Point(1617, 2641)));    //准备往站厅拐
        list.add(new RPoint(PointTag.TAG_Y, new Point(1737, 2529)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1913, 2529)));

        list.add(new RPoint(PointTag.TAG_X, new Point(2081, 2361)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2193, 2145)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2549, 2105)));

        //HA区
        list.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));    //进闸机前

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //通过闸机之后

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(3801, 2533)));    //通过后走一步，电梯方向

        list.add(new RPoint(PointTag.TAG_Y, new Point(3593, 2533)));    //再走一步，到电梯口
        list.add(new RPoint(PointTag.TAG_Y, new Point(3409, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3249, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3145, 2533)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3009, 2533)));    //电梯下


        passRouteMap.put("A进步电", list);
    }

    /**
     * A口步梯进：进步梯
     */
    private static void loadA_in_sta2sta() {
        List<RPoint> list = new ArrayList<>();

        //A口外
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3041)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3617)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 3889)));
        list.add(new RPoint(PointTag.TAG_X, new Point(721, 4065)));

        //步梯处
        list.add(new RPoint(PointTag.TAG_Y, new Point(841, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(993, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1289, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1433, 4065)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1553, 4065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3899)));

        //AD通道
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3601)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3345)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 3097)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1553, 2865)));

        list.add(new RPoint(PointTag.TAG_X, new Point(1617, 2641)));    //准备往站厅拐
        list.add(new RPoint(PointTag.TAG_Y, new Point(1737, 2529)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1913, 2529)));

        list.add(new RPoint(PointTag.TAG_X, new Point(2081, 2361)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2193, 2145)));
        list.add(new RPoint(PointTag.TAG_X, new Point(2549, 2105)));

        //HA区
        list.add(new RPoint(PointTag.TAG_Y, new Point(2939, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2105)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3719, 2105)));    //进闸机前

        //HB区域
        list.add(new RPoint(PointTag.TAG_X, new Point(3857, 2533)));    //刚好通过闸机

        //HC区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2621)));    //通过后走一步，到步梯口
        list.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2621)));
        list.add(new RPoint(PointTag.TAG_X, new Point(4481, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2513)));    //中部楼梯下


        passRouteMap.put("A进步步", list);
    }

    /**
     * B口步梯进3：进电梯3
     */
    private static void loadB_in_sta3() {
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
        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2265)));    //闸机口
        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2513)));    //刚通过闸机口

        //HC区
        list.add(new RPoint(PointTag.TAG_Y, new Point(5145, 2513)));    //进入3号电梯
        list.add(new RPoint(PointTag.TAG_Y, new Point(5305, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5449, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5561, 2513)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5705, 2513)));

        passRouteMap.put("B进步电3", list);
    }

    /**
     * B口步梯进4：进电梯4
     */
    private static void loadB_in_sta4() {
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
        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2265)));    //到闸机口
        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2513)));    //刚过闸机

        //HC区
        list.add(new RPoint(PointTag.TAG_X, new Point(5001, 2689)));    //往下走一步，准备进4号电梯

        list.add(new RPoint(PointTag.TAG_Y, new Point(5145, 2689)));    //已进入4号电梯
        list.add(new RPoint(PointTag.TAG_Y, new Point(5305, 2689)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5449, 2689)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5561, 2689)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(5705, 2689)));

        passRouteMap.put("B进步电4", list);
    }

    /**
     * D口电梯出：出到电梯
     */
    private static void loadD_out_elv2elv() {
        List<RPoint> list = new ArrayList<>();

        //HC区
        list.add(new RPoint(PointTag.TAG_Y, new Point(3000, 2673)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2673)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 2673)));

        //HD区
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出

        list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));

        //HC区:HC1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2585)));

        //HB区：HB1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1817, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1633, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 2337)));

        //AD区：AD5-AD1
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2337)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2089)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1833)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1657)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1573)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1393)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1225)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1073)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 913)));

        //D口区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1409, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1217, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1025, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(801, 913)));

        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1201)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1329)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1433)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1593)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1705)));

        passRouteMap.put("D出电电", list);
    }

    /**
     * D口电梯出：出到步梯
     */
    private static void loadD_out_elv2sta() {
        List<RPoint> list = new ArrayList<>();

        //HC区
        list.add(new RPoint(PointTag.TAG_Y, new Point(3000, 2673)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2673)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 2673)));

        //HD区
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出

        list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));

        //HC区:HC1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2585)));

        //HB区：HB1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1817, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1633, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 2337)));

        //AD区：AD5-AD1
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2337)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2089)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1833)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1657)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1573)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1393)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1225)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1073)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 913)));

        //D口区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1409, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1217, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1025, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(929, 913)));

        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1201)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1329)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1433)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1593)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1705)));

        passRouteMap.put("D出电步", list);
    }

    /**
     * D口步梯出：出到电梯
     */
    private static void loadD_out_sta2elv() {
        List<RPoint> list = new ArrayList<>();

        //HC区
        list.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2481)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2481)));
        list.add(new RPoint(PointTag.TAG_X, new Point(4481, 2481)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3929, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3777, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3617, 2641)));

        //HD区
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出

        list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));

        //HC区:HC1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2585)));

        //HB区：HB1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1817, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1633, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 2337)));

        //AD区：AD5-AD1
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2337)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2089)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1833)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1657)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1573)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1393)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1225)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1073)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 913)));

        //D口区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1409, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1217, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1025, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(801, 913)));

        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1201)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1329)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1433)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1593)));
        list.add(new RPoint(PointTag.TAG_X, new Point(801, 1705)));

        passRouteMap.put("D出步电", list);
    }

    /**
     * D口步梯出：出到步梯
     */
    private static void loadD_out_sta2sta() {
        List<RPoint> list = new ArrayList<>();

        //HC区
        list.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2481)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2481)));
        list.add(new RPoint(PointTag.TAG_X, new Point(4481, 2481)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3929, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3777, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3617, 2641)));

        //HD区
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出

        list.add(new RPoint(PointTag.TAG_X, new Point(2113, 2716)));

        //HC区:HC1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2585)));

        //HB区：HB1
        list.add(new RPoint(PointTag.TAG_X, new Point(1993, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1817, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1633, 2337)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 2337)));

        //AD区：AD5-AD1
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2337)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 2089)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1833)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1657)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1573)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1393)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1225)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 1073)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1537, 913)));

        //D口区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1537, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1409, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1217, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1025, 913)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(929, 913)));

        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1065)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1201)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1329)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1433)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1593)));
        list.add(new RPoint(PointTag.TAG_X, new Point(929, 1705)));

        passRouteMap.put("D出步步", list);
    }

    /**
     * A口电梯出：出到电梯
     */
    private static void loadA_out_elv2elv() {
        List<RPoint> list = new ArrayList<>();

        //HC区
        list.add(new RPoint(PointTag.TAG_Y, new Point(3000, 2673)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3329, 2673)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 2673)));

        //HD区
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出


        //HD区：HD1
        list.add(new RPoint(PointTag.TAG_Y, new Point(1961, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1737, 3057)));

        //AD区：AD7-AD11
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3225)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3353)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3585)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3833)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 4001)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 4129)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 4281)));

        //A口区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1609, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1449, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1305, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(993, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(721, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(513, 4281)));

        list.add(new RPoint(PointTag.TAG_X, new Point(513, 4081)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3905)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3753)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3577)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3393)));

        passRouteMap.put("A出电电", list);
    }

    /**
     * A口步梯出：出到电梯
     */
    private static void loadA_out_sta2elv() {
        List<RPoint> list = new ArrayList<>();

        //HC区
        list.add(new RPoint(PointTag.TAG_Y, new Point(4177, 2481)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4353, 2481)));
        list.add(new RPoint(PointTag.TAG_X, new Point(4481, 2481)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4481, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(4109, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3929, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3777, 2641)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3617, 2641)));

        //HD区
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 2887)));
        list.add(new RPoint(PointTag.TAG_X, new Point(3577, 3057)));

        list.add(new RPoint(PointTag.TAG_Y, new Point(3577, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(3134, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2744, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2354, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(2113, 3057)));    //这一步之后可往A口出


        //HD区：HD1
        list.add(new RPoint(PointTag.TAG_Y, new Point(1961, 3057)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1737, 3057)));

        //AD区：AD7-AD11
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3225)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3353)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3585)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 3833)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 4001)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 4129)));
        list.add(new RPoint(PointTag.TAG_X, new Point(1737, 4281)));

        //A口区域
        list.add(new RPoint(PointTag.TAG_Y, new Point(1609, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1449, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1305, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(1121, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(993, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(721, 4281)));
        list.add(new RPoint(PointTag.TAG_Y, new Point(513, 4281)));

        list.add(new RPoint(PointTag.TAG_X, new Point(513, 4081)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3905)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3753)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3577)));
        list.add(new RPoint(PointTag.TAG_X, new Point(513, 3393)));

        passRouteMap.put("A出步电", list);
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
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 25 * rPoint.getRandMulti()));
            point.setY(rPoint.getY());
        } else if (rPoint.getFlag() == PointTag.TAG_Y) {
            point.setX(rPoint.getX());
//            point.setY(rPoint.getY() + new Random().nextInt(10));
            point.setY(rPoint.getY() +
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 25 * rPoint.getRandMulti()));
        } else {
            point.setX(rPoint.getX() +
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 25 * rPoint.getRandMulti()));
            point.setY(rPoint.getY() +
                    (int) (String.valueOf(System.nanoTime() + new Random().nextLong()).hashCode() % 25 * rPoint.getRandMulti()));
        }

        //再确定位置：站台/站厅
        point.setZ(rPoint.getZ());

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
