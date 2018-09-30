/**
 * FileName: ServiceDiscoeryImpl
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 12:46
 */

package com.rxt.common.zk;

import com.rxt.common.zk.loadBanlance.LoadBanlance;
import com.rxt.common.zk.loadBanlance.RandomLoadBanlance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

public class ServiceDiscoeryImpl implements IServiceDiscovery {

    List<String> repos = new ArrayList<>();

    private CuratorFramework curatorFramework;
    private String address;    //定义不同的注册中心


    public ServiceDiscoeryImpl(String address) {
        this.address = address;
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(this.address)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    @Override
    public String discover(String serviceName) {
        String path = zkConfig.ZK_REGISTER_PATH + "/" + serviceName;

        try {
            repos = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw  new RuntimeException("获取子节点异常：" + e);
        }

        // 动态发现服务节点的变化
        registryWatcher(path);

        // 做随机负载
        LoadBanlance loadBanlance = new RandomLoadBanlance();    //随机负载

        return loadBanlance.selectHost(repos);    //返回负载均衡后的地址
    }

    private void registryWatcher(final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, path, true);

        //定义监听事件
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(pathChildrenCacheListener);    //添加节点监听
        try {
            childrenCache.start();    //启动监听
        } catch (Exception e) {
            throw new RuntimeException("注册 PathChild Watcher 异常" + e);
        }


    }
}

















