/**
 * FileName: JedisConnectionUtils
 * Author:   Ren Xiaotian
 * Date:     2018/10/17 17:39
 */

package com.rxt.common.utils.DistributedLock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接工具类
 */
public class JedisConnectionUtils {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        pool = new JedisPool(jedisPoolConfig, "192.168.117.134", 6379);
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

}
