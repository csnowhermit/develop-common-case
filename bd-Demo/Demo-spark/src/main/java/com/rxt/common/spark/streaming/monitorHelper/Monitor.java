package com.rxt.common.spark.streaming.monitorHelper;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 监控网站流量：用于实时统计
 * 图表展现：直接从Redis中取数，横坐标，时间间距(5秒，30秒，1分，等)；纵坐标：流量数
 * 计算：System.currentTimeMillis()/步长 × 步长，得到所属于哪个时间间距
 * 存储：Redis，SortedSet，zadd；key=监控主题_时间步长
 * eg：zadd monitorset_5 20181219102800 50
 * zadd monitorset_5 20181219102805 60
 */
public class Monitor {

    private static Jedis jedis = null;

    static {
        jedis = new Jedis("192.168.117.134", 6379);
        jedis.auth("123456");
    }

    public static void main(String[] args) throws InterruptedException {
        //1 2 3 4 5 6 7 8 9 10    //监控到的每秒
        //[0, 5, 10]              //步长
//        int[] seconds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        for (int second : seconds) {
//            System.out.println(second + ": " + second / 5 * 5);
//        }
        String[] urls = new String[]{"baidu.com", "google.com", "bing.com", "yaho.com"};
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            count(urls[i % 4], random.nextInt(20));
            Thread.sleep(5000);
        }

    }


    public static void count(final String url, final int clicks) {
        final long currSecond = System.currentTimeMillis() / 1000;    //当前秒数

        //所有步长都算，展示时直接读取
        for (Integer prec : RedisConstants.PRCISION) {
            long startSlice = currSecond / prec * prec;    //算出在哪个时间批次
            String key = String.format("%s:%s", prec, url);    //Redis中保存的键

            //因为在键或member不存在时，zincrBy会执行zadd；存在时，相同键相同member的score累加
            jedis.zincrby(("Count:" + key).getBytes(), clicks, String.valueOf(startSlice).getBytes());
        }
        System.out.println("已记录：" + url + " " + clicks);
    }

}
