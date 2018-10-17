/**
 * FileName: RedissonDLock
 * Author:   Ren Xiaotian
 * Date:     2018/10/17 18:48
 */

package com.rxt.common.utils.DistributedLock.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * 基于Redis的分布式锁：Redisson
 */
public class RedissonDLock {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.117.134:6379");
        config.useSingleServer().setPassword("123456");
        RedissonClient redissonClient = Redisson.create(config);
        RLock rLock = redissonClient.getLock("DLock");

        try {

            rLock.tryLock(100, 10, TimeUnit.SECONDS);    //获取锁

            Thread.sleep(20000);

            rLock.unlock();    //释放锁

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
            redissonClient.shutdown();
        }
    }

}
