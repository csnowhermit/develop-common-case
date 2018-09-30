/**
 * FileName: RegisterCenterImpl
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 11:14
 */

package com.rxt.common.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.CONNECTION_STR)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        // 注册相应的服务
        String servicePath = zkConfig.ZK_REGISTER_PATH + "/" + serviceName;

        try {
            // 如果父节点不存在，则创建（父节点为 根路径+服务名称）
            if(curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }

            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().
                    withMode(CreateMode.EPHEMERAL)    //创建临时节点
                    .forPath(addressPath, "0".getBytes());
            System.out.println("服务注册成功：" + rsNode);    //rsNode为服务的全路径名


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
