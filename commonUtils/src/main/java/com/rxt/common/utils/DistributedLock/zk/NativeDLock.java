/**
 * FileName: nativeDLock
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 11:22
 */

package com.rxt.common.utils.DistributedLock.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于zookeeper原生API实现分布式锁
 */
public class NativeDLock implements Lock, Watcher {
    private ZooKeeper zooKeeper = null;
    private String ROOT_LOCK = "/locks";    //定义根节点
    private String WAIT_LOCK;    //表示前一个锁
    private String CURRENT_LOCK;    //表示当前的锁

    private CountDownLatch countDownLatch;

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public String getROOT_LOCK() {
        return ROOT_LOCK;
    }

    public void setROOT_LOCK(String ROOT_LOCK) {
        this.ROOT_LOCK = ROOT_LOCK;
    }

    public String getWAIT_LOCK() {
        return WAIT_LOCK;
    }

    public void setWAIT_LOCK(String WAIT_LOCK) {
        this.WAIT_LOCK = WAIT_LOCK;
    }

    public String getCURRENT_LOCK() {
        return CURRENT_LOCK;
    }

    public void setCURRENT_LOCK(String CURRENT_LOCK) {
        this.CURRENT_LOCK = CURRENT_LOCK;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public NativeDLock() {
        try {
            zooKeeper = new ZooKeeper("192.168.117.101:2181,192.168.117.102:2181,192.168.117.103:2181", 4000, this);
//            Thread.sleep(10000);
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);    //不需要再次注册对根节点的监听

            if (stat == null) {    //如果根节点不存在，则自定义一个持久化的根节点
                zooKeeper.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if (this.tryLock()) {
            System.out.println(Thread.currentThread().getName() + " ==> " + CURRENT_LOCK + " 获得锁成功");
        }
        try {
            wait4Lock(WAIT_LOCK);    //没有获得锁，继续等待获得锁
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待锁
     *
     * @param prev 当前节点的上一个节点
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    private boolean wait4Lock(String prev) throws KeeperException, InterruptedException {
        Stat stat = null;

        if (prev != null) {
            stat = zooKeeper.exists(prev, true);    //监听当前节点的上一个节点
        }
        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + " ==> 等待锁 " + ROOT_LOCK + "/" + prev + " 释放");
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();    //如果锁没释放，就一直等待锁释放
            System.out.println(Thread.currentThread().getName() + " 获得锁成功");
            return true;
        }
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        // 尝试获得锁时，直接在指定目录下创建一个临时有序节点
        try {
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/", "0".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + " ==> " + CURRENT_LOCK + " 尝试竞争锁");

            List<String> childrens = zooKeeper.getChildren(ROOT_LOCK, false);    //获得根节点下所有子节点
            SortedSet<String> sortedSet = new TreeSet<String>();    //定义一个集合进行排序

            for (String children : childrens) {
                sortedSet.add(ROOT_LOCK + "/" + children);
            }

            String firstNode = sortedSet.first();    //获取当前子节点中最小的节点
            SortedSet<String> lessThanMe = ((TreeSet<String>) sortedSet).headSet(CURRENT_LOCK);

            if (CURRENT_LOCK.equals(firstNode)) {    //当前节点和最小的节点进行比较，如果相等，则获得锁
                return true;
            }

            if (!lessThanMe.isEmpty()) {    //如果子节点中比当前节点小的节点集合非空
                WAIT_LOCK = lessThanMe.last();    //则将比当前节点小的最后一个节点设为等待
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + "释放锁");
        try {
            //释放锁
            zooKeeper.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 监听watch事件，一旦监听的节点有变化，则触发该函数
     *
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    NativeDLock nativeDLock = new NativeDLock();
                    nativeDLock.lock();    //获得锁
                    Thread.sleep(1000);    //睡眠1秒钟
                    nativeDLock.unlock();    //释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();

            countDownLatch.countDown();
        }
        System.in.read();
    }
}
