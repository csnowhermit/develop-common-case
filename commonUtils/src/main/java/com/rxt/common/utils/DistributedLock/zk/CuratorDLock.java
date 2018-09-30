/**
 * FileName: CuratorDLock
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 14:43
 */

package com.rxt.common.utils.DistributedLock.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;

/**
 * 使用Curator包实现分布式锁
 */
public class CuratorDLock {
    public static void main(String[] args) throws Exception {

        String connStr = "192.168.117.101:2181,192.168.117.102:2181,192.168.117.103:2181";
        String ROOT_LOCK = "/locks";

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connStr)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();

        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, ROOT_LOCK);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        curatorFramework.start();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
//                    countDownLatch.wait();

                    interProcessMutex.acquire();    //获得锁
                    System.out.println(Thread.currentThread().getName() + " 获得锁");
                    Thread.sleep(1000);
                    interProcessMutex.release();    //释放锁
                    System.out.println(Thread.currentThread().getName() + " 释放锁");

                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }




        //        curatorFramework.start();
//        curatorFramework.create().creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .forPath(ROOT_LOCK, "0".getBytes());


//        //结果: /curator/mic/node1
//        //原生api中，必须是逐层创建，也就是父节点必须存在，子节点才能创建
//        /*curatorFramework.create().creatingParentsIfNeeded().
//                withMode(CreateMode.PERSISTENT).
//                forPath("/mic/node1","1".getBytes());*/
//        //删除
////        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/mic/node1");
//
//        Stat stat=new Stat();
//        curatorFramework.getData().storingStatIn(stat).forPath("/mic/node1");
//
//        curatorFramework.setData().
//                withVersion(stat.getVersion()).forPath("/mic/node1","xx".getBytes());
//
//        curatorFramework.close();


    }
}
