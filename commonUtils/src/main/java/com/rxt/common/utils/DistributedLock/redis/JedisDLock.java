/**
 * FileName: JedisDLock
 * Author:   Ren Xiaotian
 * Date:     2018/10/17 17:31
 */

package com.rxt.common.utils.DistributedLock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * 基于Redis的分布式锁:Jedis
 */
public class JedisDLock {

    private String password = "123456";

    /**
     * 获取锁
     *
     * @param lockName       锁名称
     * @param acquireTimeout 获取锁的超时时间
     * @param lockTimeout    锁本身的过期时间
     * @return 返回获取锁的ID
     */
    public String acquireLock(String lockName, long acquireTimeout, long lockTimeout) {
        String identifier = UUID.randomUUID().toString();    //确保释放锁的时候是同一个持有锁的人
        String lockKey = "lock:" + lockName;   //锁的名字
        int lockExpire = (int) (lockTimeout / 1000);    //设置超时时间
        Jedis jedis = null;

        try {
            jedis = JedisConnectionUtils.getJedis();
            jedis.auth(password);
            long end = System.currentTimeMillis() + acquireTimeout;

            while (System.currentTimeMillis() < end) {    //在这段时间内不断循环获得锁
                if (jedis.setnx(lockKey, identifier) == 1) {    //设置值成功
//                    try {
//                        throw new IOException("此处模拟设置值成功之后，设置超时时间之前程序挂掉");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
                    jedis.expire(lockKey, lockExpire);    //设置超时时间
                    return identifier;    //获得锁成功
                }

                if (jedis.ttl(lockKey) == -1) {    //如果没有设置超时时间，则设置超时时间
                    jedis.expire(lockKey, lockExpire);
                }
                try {
                    Thread.sleep(100);    //如果获取失败，稍等片刻后进行获取锁的重试，（立马重试没有任何意义）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            jedis.close();    //关闭Jedis
        }
        return null;
    }

    /**
     * 使用lua脚本释放锁
     *
     * @param lockName   锁名称
     * @param identifier 要释放锁的ID
     * @return 返回true，释放成功；否则，释放失败
     */
    public boolean releaseLockWithLua(String lockName, String identifier) {
        System.out.println(lockName + " 开始释放锁With Lua：" + identifier);

        Jedis jedis = JedisConnectionUtils.getJedis();
        jedis.auth(password);
        String lockKey = "lock:" + lockName;

        String lua = "if redis.call(\"get\",KEYS[1])==ARGV[1] then " +
                "return redis.call(\"del\",KEYS[1]) " +
                "else return 0 end";
        Long rs = (Long) jedis.eval(lua, 1, new String[]{lockKey, identifier});
        if (rs.intValue() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockName   锁名称
     * @param identifier 要释放锁的ID
     * @return 返回true，释放成功；否则，释放失败
     */
    public boolean releaseLock(String lockName, String identifier) {
        System.out.println(lockName + " 开始释放锁：" + identifier);

        String lockKey = "lock:" + lockName;
        Jedis jedis = null;
        boolean isRelease = false;    //默认没有被释放
        try {
            jedis = JedisConnectionUtils.getJedis();
            jedis.auth(password);
            while (true) {    //循环，确保锁一定会被释放
                jedis.watch(lockKey);
                String currLock = jedis.get(lockKey);
                System.out.println(identifier + " 在Redis中的锁为：" + currLock);
                if (identifier.equals(currLock)) {    //判断是否为同一把锁
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    if (transaction.exec().isEmpty()) {
                        continue;
                    }
                    isRelease = true;
                }
                jedis.unwatch();
                break;
            }
        } finally {
            jedis.close();    //关闭连接
        }
        return isRelease;
    }

    public static void main(String[] args) throws InterruptedException {
//        JedisDLock jedisDLock = new JedisDLock();
//        String identifier = jedisDLock.acquireLock("DLock", System.currentTimeMillis(), 50000);
//        System.out.println("获取锁的ID为：" + identifier);
//
//        Thread.sleep(20000);
//        System.out.println(jedisDLock.releaseLockWithLua("DLock", identifier));
//
//        Thread.sleep(20000);
//
//        identifier = jedisDLock.acquireLock("DLock", System.currentTimeMillis(), 50000);
//        System.out.println("获取锁的ID为：" + identifier);
//
//        Thread.sleep(20000);
//        System.out.println(jedisDLock.releaseLock("DLock", identifier));

//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        for (int i = 0; i < 10; i++) {
//            new Thread(new DLockTest()).start();
//            countDownLatch.countDown();
//        }
//
//        countDownLatch.await();
        for (int i = 0; i < 10; i++) {
            new Thread(new DLockTest()).start();
        }

    }
}
