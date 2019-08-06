package com.rxt.common.autoFlow;

import java.util.HashMap;
import java.util.Map;

/**
 * 各个Region
 */
public class ContextParam {

    /**
     * <区域编号，区域范围>
     */
    private static Map<String, Region> regionMap = new HashMap<>();

    /**
     * 类启动时候执行静态块，加载预置化操作
     */
    static {
        if (regionMap.size() == 0) {
            loadExit_A();
            loadExit_B();
            loadExit_C();
            loadExit_D();
            loadWay_AD();
            loadWay_BC();
            loadHall();
        }
    }

    /**
     * 加载A口区域
     */
    private static void loadExit_A() {
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

    public static void print() {
        for (String key : regionMap.keySet()) {
            System.out.println(key + " --> " + regionMap.get(key));
        }
    }


}
