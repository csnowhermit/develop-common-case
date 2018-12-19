package com.rxt.common.spark.streaming.monitorHelper;

public class RedisConstants {

    /**
     * 单位时间：单位：秒
     */
    public static final Integer[] PRCISION = new Integer[]{5,
            30,
            60,         //1分钟
            300,        //5分钟
            3600,       //1小时
            18000,      //5小时
            86400};     //24小时

}
