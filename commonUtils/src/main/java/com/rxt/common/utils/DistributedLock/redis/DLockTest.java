/**
 * FileName: DLockTest
 * Author:   Ren Xiaotian
 * Date:     2018/11/4 10:51
 */

package com.rxt.common.utils.DistributedLock.redis;

/**
 * 模拟同时获得锁的多线程
 */
public class DLockTest implements Runnable {
    @Override
    public void run() {
        JedisDLock jedisDLock = new JedisDLock();
        String identifier = jedisDLock.acquireLock("DLock", System.currentTimeMillis(), 50000);
//        String identifier = jedisDLock.acquireLockUsingMulti("DLock", System.currentTimeMillis(), 50000);
        System.out.println(Thread.currentThread().getName() + " 获得锁：" + identifier);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jedisDLock.releaseLock("DLock", identifier);
        System.out.println(Thread.currentThread().getName() + " 释放锁：" + identifier);
        System.out.println("=======================");
    }
}
