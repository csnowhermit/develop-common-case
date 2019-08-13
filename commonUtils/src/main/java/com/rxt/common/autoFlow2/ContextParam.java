package com.rxt.common.autoFlow2;

import java.util.*;

/**
 * 各个Region
 */
public class ContextParam {

    /**
     * <区域编号，区域范围>
     */
    private static Map<String, Region> regionMap = new HashMap<>();

    /**
     * <进出类型，路径Region列表>
     */
    private static Map<String, List<String>> passRouteMap = new HashMap<>();

    public static Map<String, Region> getRegionMap() {
        return regionMap;
    }

    public static Map<String, List<String>> getPassRouteMap() {
        return passRouteMap;
    }

    /**
     * 类启动时候执行静态块，加载预置化操作
     */
    static {
        if (regionMap.size() == 0) {
//            loadExit_A();
//            loadExit_B();
//            loadExit_C();
            loadExit_D();
//            loadWay_AD();
//            loadWay_BC();
//            loadHall();
        }
        if (passRouteMap.size() == 0) {
            loadPassRoute();
        }
    }

    /**
     * 加载A口区域
     */
    private static void loadExit_A() {
        //1.进站通道
        regionMap.put("A1", new Region("A1",
                new Point(801, 4013),
                new Point(801, 4333),
                new Point(1125, 4013),
                new Point(1125, 4333)));
        regionMap.put("A2", new Region("A2",
                new Point(1125, 4013),
                new Point(1125, 4333),
                new Point(1449, 4013),
                new Point(1449, 4333)));

        //2.出站通道

    }

    /**
     * 加载B口区域
     */
    private static void loadExit_B() {
        regionMap.put("B1", new Region("B1",
                new Point(5905, 4465),
                new Point(5905, 4889),
                new Point(6317, 4465),
                new Point(6317, 4489)));
        regionMap.put("B2", new Region("B2",
                new Point(6317, 4465),
                new Point(6317, 4489),
                new Point(6729, 4465),
                new Point(6729, 4889)));
        regionMap.put("B3", new Region("B3",
                new Point(6521, 4313),
                new Point(6521, 4465),
                new Point(7201, 4313),
                new Point(7201, 4465)));
    }

    /**
     * 加载C口区域：C口未开放，区域为空
     */
    private static void loadExit_C() {
    }

    /**
     * 加载D口区域
     */
    private static void loadExit_D() {
        //1.进站通道
        regionMap.put("D1", new Region("D1",
                new Point(553, 825),
                new Point(553, 1115),
                new Point(1057, 825),
                new Point(1057, 1115)));
        regionMap.put("D2", new Region("D2",
                new Point(553, 1115),
                new Point(553, 1405),
                new Point(1057, 1115),
                new Point(1057, 1405)));
        regionMap.put("D3", new Region("D3",
                new Point(553, 1405),
                new Point(553, 1697),
                new Point(1057, 1405),
                new Point(1057, 1697)));
        regionMap.put("D4", new Region("D4",
                new Point(1057, 825),
                new Point(1057, 953),
                new Point(1449, 825),
                new Point(1449, 953)));
    }

    /**
     * 加载AD通道
     */
    private static void loadWay_AD() {
        regionMap.put("AD1", new Region("AD1",
                new Point(1449, 825),
                new Point(1449, 1143),
                new Point(1769, 825),
                new Point(1785, 1143)));
        regionMap.put("AD2", new Region("AD2",
                new Point(1449, 1143),
                new Point(1449, 1461),
                new Point(1785, 1143),
                new Point(1785, 1461)));
        regionMap.put("AD3", new Region("AD3",
                new Point(1449, 1461),
                new Point(1449, 1779),
                new Point(1785, 1461),
                new Point(1785, 1779)));
        regionMap.put("AD4", new Region("AD4",
                new Point(1449, 1779),
                new Point(1449, 2097),
                new Point(1785, 1779),
                new Point(1785, 2097)));
        regionMap.put("AD5", new Region("AD5",
                new Point(1449, 2097),
                new Point(1449, 2415),
                new Point(1785, 2097),
                new Point(1785, 2415)));
        regionMap.put("AD6", new Region("AD6",
                new Point(1449, 2415),
                new Point(1449, 2733),
                new Point(1785, 2415),
                new Point(1785, 2733)));
        regionMap.put("AD7", new Region("AD7",
                new Point(1449, 2733),
                new Point(1449, 3051),
                new Point(1785, 2733),
                new Point(1785, 3051)));
        regionMap.put("AD8", new Region("AD8",
                new Point(1449, 3051),
                new Point(1449, 3369),
                new Point(1785, 3051),
                new Point(1785, 3369)));
        regionMap.put("AD9", new Region("AD9",
                new Point(1449, 3369),
                new Point(1449, 3687),
                new Point(1785, 3369),
                new Point(1785, 3687)));
        regionMap.put("AD10", new Region("AD10",
                new Point(1449, 3687),
                new Point(1449, 4005),
                new Point(1785, 3687),
                new Point(1785, 4005)));
        regionMap.put("AD11", new Region("AD11",
                new Point(1449, 4005),
                new Point(1449, 4333),
                new Point(1785, 4005),
                new Point(1785, 4333)));
    }

    /**
     * 加载BC通道
     */
    private static void loadWay_BC() {
        regionMap.put("BC1", new Region("BC1",
                new Point(6849, 1825),
                new Point(6849, 2180),
                new Point(7201, 1825),
                new Point(7201, 2180)));
        regionMap.put("BC2", new Region("BC2",
                new Point(6849, 2180),
                new Point(6849, 2535),
                new Point(7201, 2180),
                new Point(7201, 2535)));
        regionMap.put("BC3", new Region("BC3",
                new Point(6849, 2535),
                new Point(6849, 2890),
                new Point(7201, 2535),
                new Point(7201, 2890)));
        regionMap.put("BC4", new Region("BC4",
                new Point(6849, 2890),
                new Point(6849, 3245),
                new Point(7201, 2890),
                new Point(7201, 3245)));
        regionMap.put("BC5", new Region("BC5",
                new Point(6849, 3245),
                new Point(6849, 3600),
                new Point(7201, 3245),
                new Point(7201, 3600)));
        regionMap.put("BC6", new Region("BC6",
                new Point(6849, 3600),
                new Point(6849, 3955),
                new Point(7201, 3600),
                new Point(7201, 3955)));
        regionMap.put("BC7", new Region("BC7",
                new Point(6849, 3955),
                new Point(6849, 4313),
                new Point(7201, 3955),
                new Point(7201, 4313)));
    }

    /**
     * 加载站厅
     */
    private static void loadHall() {
        // 站厅Region分为四行，每行分为13个Region
        // 第一行
        regionMap.put("HA1", new Region("HA1",
                new Point(1769, 1825),
                new Point(1769, 2179),
                new Point(2159, 1825),
                new Point(2159, 2179)));
        regionMap.put("HA2", new Region("HA2",
                new Point(2159, 1825),
                new Point(2159, 2179),
                new Point(2549, 1825),
                new Point(2549, 2179)));
        regionMap.put("HA3", new Region("HA3",
                new Point(2549, 1825),
                new Point(2549, 2179),
                new Point(2939, 1825),
                new Point(2939, 2179)));
        regionMap.put("HA4", new Region("HA4",
                new Point(2939, 1825),
                new Point(2939, 2179),
                new Point(3329, 1825),
                new Point(3329, 2179)));
        regionMap.put("HA5", new Region("HA5",
                new Point(3329, 1825),
                new Point(3329, 2179),
                new Point(3719, 1825),
                new Point(3719, 2179)));
        regionMap.put("HA6", new Region("HA6",
                new Point(3719, 1825),
                new Point(3719, 2179),
                new Point(4109, 1825),
                new Point(4109, 2179)));
        regionMap.put("HA7", new Region("HA7",
                new Point(4109, 1825),
                new Point(4109, 2179),
                new Point(4499, 1825),
                new Point(4499, 2179)));
        regionMap.put("HA8", new Region("HA8",
                new Point(4499, 1825),
                new Point(4499, 2179),
                new Point(4889, 1825),
                new Point(4889, 2179)));
        regionMap.put("HA9", new Region("HA9",
                new Point(4889, 1825),
                new Point(4889, 2179),
                new Point(5279, 1825),
                new Point(5279, 2179)));
        regionMap.put("HA10", new Region("HA10",
                new Point(5279, 1825),
                new Point(5279, 2179),
                new Point(5669, 1825),
                new Point(5669, 2179)));
        regionMap.put("HA11", new Region("HA11",
                new Point(5669, 1825),
                new Point(5669, 2179),
                new Point(6059, 1825),
                new Point(6059, 2179)));
        regionMap.put("HA12", new Region("HA12",
                new Point(6059, 1825),
                new Point(6059, 2179),
                new Point(6449, 1825),
                new Point(6449, 2179)));
        regionMap.put("HA13", new Region("HA13",
                new Point(6449, 1825),
                new Point(6449, 2179),
                new Point(6849, 1825),
                new Point(6849, 2179)));

        //第二行
        regionMap.put("HB1", new Region("HB1",
                new Point(1769, 2179),
                new Point(1769, 2533),
                new Point(2159, 2179),
                new Point(2159, 2533)));
        regionMap.put("HB2", new Region("HB2",
                new Point(2159, 2179),
                new Point(2159, 2533),
                new Point(2549, 2179),
                new Point(2549, 2533)));
        regionMap.put("HB3", new Region("HB3",
                new Point(2549, 2179),
                new Point(2549, 2533),
                new Point(2939, 2179),
                new Point(2939, 2533)));
        regionMap.put("HB4", new Region("HB4",
                new Point(2939, 2179),
                new Point(2939, 2533),
                new Point(3329, 2179),
                new Point(3329, 2533)));
        regionMap.put("HB5", new Region("HB5",
                new Point(3329, 2179),
                new Point(3329, 2533),
                new Point(3719, 2179),
                new Point(3719, 2533)));
        regionMap.put("HB6", new Region("HB6",
                new Point(3719, 2179),
                new Point(3719, 2533),
                new Point(4109, 2179),
                new Point(4109, 2533)));
        regionMap.put("HB7", new Region("HB7",
                new Point(4109, 2179),
                new Point(4109, 2533),
                new Point(4499, 2179),
                new Point(4499, 2533)));
        regionMap.put("HB8", new Region("HB8",
                new Point(4499, 2179),
                new Point(4499, 2533),
                new Point(4889, 2179),
                new Point(4889, 2533)));
        regionMap.put("HB9", new Region("HB9",
                new Point(4889, 2179),
                new Point(4889, 2533),
                new Point(5279, 2179),
                new Point(5279, 2533)));
        regionMap.put("HB10", new Region("HB10",
                new Point(5279, 2179),
                new Point(5279, 2533),
                new Point(5669, 2179),
                new Point(5669, 2533)));
        regionMap.put("HB11", new Region("HB11",
                new Point(5669, 2179),
                new Point(5669, 2533),
                new Point(6059, 2179),
                new Point(6059, 2533)));
        regionMap.put("HB12", new Region("HB12",
                new Point(6059, 2179),
                new Point(6059, 2533),
                new Point(6449, 2179),
                new Point(6449, 2533)));
        regionMap.put("HB13", new Region("HB13",
                new Point(6449, 2179),
                new Point(6449, 2533),
                new Point(6849, 2179),
                new Point(6849, 2533)));

        //第三行
        regionMap.put("HC1", new Region("HC1",
                new Point(1769, 2533),
                new Point(1769, 2887),
                new Point(2159, 2533),
                new Point(2159, 2887)));
        regionMap.put("HC2", new Region("HC2",
                new Point(2159, 2533),
                new Point(2159, 2887),
                new Point(2549, 2533),
                new Point(2549, 2887)));
        regionMap.put("HC3", new Region("HC3",
                new Point(2549, 2533),
                new Point(2549, 2887),
                new Point(2939, 2533),
                new Point(2939, 2887)));
        regionMap.put("HC4", new Region("HC4",
                new Point(2939, 2533),
                new Point(2939, 2887),
                new Point(3329, 2533),
                new Point(3329, 2887)));
        regionMap.put("HC5", new Region("HC5",
                new Point(3329, 2533),
                new Point(3329, 2887),
                new Point(3719, 2533),
                new Point(3719, 2887)));
        regionMap.put("HC6", new Region("HC6",
                new Point(3719, 2533),
                new Point(3719, 2887),
                new Point(4109, 2533),
                new Point(4109, 2887)));
        regionMap.put("HC7", new Region("HC7",
                new Point(4109, 2533),
                new Point(4109, 2887),
                new Point(4499, 2533),
                new Point(4499, 2887)));
        regionMap.put("HC8", new Region("HC8",
                new Point(4499, 2533),
                new Point(4499, 2887),
                new Point(4889, 2533),
                new Point(4889, 2887)));
        regionMap.put("HC9", new Region("HC9",
                new Point(4889, 2533),
                new Point(4889, 2887),
                new Point(5279, 2533),
                new Point(5279, 2887)));
        regionMap.put("HC10", new Region("HC10",
                new Point(5279, 2533),
                new Point(5279, 2887),
                new Point(5669, 2533),
                new Point(5669, 2887)));
        regionMap.put("HC11", new Region("HC11",
                new Point(5669, 2533),
                new Point(5669, 2887),
                new Point(6059, 2533),
                new Point(6059, 2887)));
        regionMap.put("HC12", new Region("HC12",
                new Point(6059, 2533),
                new Point(6059, 2887),
                new Point(6449, 2533),
                new Point(6449, 2887)));
        regionMap.put("HC13", new Region("HC13",
                new Point(6449, 2533),
                new Point(6449, 2887),
                new Point(6849, 2533),
                new Point(6849, 2887)));

        //第四行
        regionMap.put("HD1", new Region("HD1",
                new Point(1769, 2887),
                new Point(1769, 3241),
                new Point(2159, 2887),
                new Point(2159, 3241)));
        regionMap.put("HD2", new Region("HD2",
                new Point(2159, 2887),
                new Point(2159, 3241),
                new Point(2549, 2887),
                new Point(2549, 3241)));
        regionMap.put("HD3", new Region("HD3",
                new Point(2549, 2887),
                new Point(2549, 3241),
                new Point(2939, 2887),
                new Point(2939, 3241)));
        regionMap.put("HD4", new Region("HD4",
                new Point(2939, 2887),
                new Point(2939, 3241),
                new Point(3329, 2887),
                new Point(3329, 3241)));
        regionMap.put("HD5", new Region("HD5",
                new Point(3329, 2887),
                new Point(3329, 3241),
                new Point(3719, 2887),
                new Point(3719, 3241)));
        regionMap.put("HD6", new Region("HD6",
                new Point(3719, 2887),
                new Point(3719, 3241),
                new Point(4109, 2887),
                new Point(4109, 3241)));
        regionMap.put("HD7", new Region("HD7",
                new Point(4109, 2887),
                new Point(4109, 3241),
                new Point(4499, 2887),
                new Point(4499, 3241)));
        regionMap.put("HD8", new Region("HD8",
                new Point(4499, 2887),
                new Point(4499, 3241),
                new Point(4889, 2887),
                new Point(4889, 3241)));
        regionMap.put("HD9", new Region("HD9",
                new Point(4889, 2887),
                new Point(4889, 3241),
                new Point(5279, 2887),
                new Point(5279, 3241)));
        regionMap.put("HD10", new Region("HD10",
                new Point(5279, 2887),
                new Point(5279, 3241),
                new Point(5669, 2887),
                new Point(5669, 3241)));
        regionMap.put("HD11", new Region("HD11",
                new Point(5669, 2887),
                new Point(5669, 3241),
                new Point(6059, 2887),
                new Point(6059, 3241)));
        regionMap.put("HD12", new Region("HD12",
                new Point(6059, 2887),
                new Point(6059, 3241),
                new Point(6449, 2887),
                new Point(6449, 3241)));
        regionMap.put("HD13", new Region("HD13",
                new Point(6449, 2887),
                new Point(6449, 3241),
                new Point(6849, 2887),
                new Point(6849, 3241)));
    }

    /**
     * 加载预置化客流路径
     */
    private static void loadPassRoute() {
        passRouteMap.put("A进", Arrays.asList("A1", "A2", "AD11", "AD10", "AD9", "AD8", "AD7", "AD6", "HC1", "HB1", "HB2", "HB3", "HB4", "HB5", "HA5", "HA6", "HB6", "HC6", "HC7", "HC8", "HC9", "HC10", "HC11"));
        passRouteMap.put("B进", Arrays.asList("B1", "B2", "B3", "BC7", "BC6", "BC5", "BC4", "BC3", "BC2", "BC1", "HA13", "HA12", "HA11", "HA10", "HA9", "HB9", "HC9", "HC10", "HC11"));
        passRouteMap.put("D进", Arrays.asList("D3", "D2", "D1", "D4", "AD1", "AD2", "AD3", "AD4", "HA1", "HA2", "HA3", "HA4", "HA5", "HA6", "HB6", "HC6", "HC7", "HC8", "HC9", "HC10", "HC11"));

        passRouteMap.put("A出", Arrays.asList("HC4", "HC5", "HD5", "HD4", "HD3", "HD2", "HD1", "AD7", "AD8", "AD9", "AD10", "AD11", "A2", "A1"));
        //B口只进不出
//        passRouteMap.put("B出", Arrays.asList());
        passRouteMap.put("D出", Arrays.asList("HC4", "HC5", "HD5", "HD4", "HD3", "HD2", "HC1", "HB1", "AD5", "AD4", "AD3", "AD2", "AD1", "D4", "D1", "D2", "D3"));
    }

    /**
     * 随机生成区域内坐标点
     *
     * @param tag 1，绝对坐标；其他，相对坐标
     * @param regionFlag 区域标识符
     * @return 返回该区域内随机坐标点
     */
    public static Point randomPoint(int tag, String regionFlag) {
        //1.根据regionFlag找到块的区域对象
        Region tmp = regionMap.get(regionFlag);

        //2.找到该区域的左上角和右下角
        Point leftup = tmp.getLeftup();
        Point rightdown = tmp.getRightdown();

        //3.随机点的坐标：
        //  横坐标：左上横坐标+random（右下横-左上横）
        //  纵坐标：左上纵坐标+random（右下纵-左上纵）
        Point point = new Point();
        if (tag==1) {
            point.setX(leftup.getX() + new Random().nextInt(rightdown.getX() - leftup.getX()));
            point.setY(leftup.getY() + new Random().nextInt(rightdown.getY() - leftup.getY()));
        }else{
            point.setX(new Random().nextInt(rightdown.getX() - leftup.getX()));
            point.setY(new Random().nextInt(rightdown.getY() - leftup.getY()));
        }
        return new RPoint(regionFlag, point);
    }


    public static void print() {
        for (String key : regionMap.keySet()) {
            System.out.println(key + " --> " + regionMap.get(key));
        }
        for (String key : passRouteMap.keySet()) {
            System.out.println(key + " --> " + passRouteMap.get(key));
        }
    }


}
