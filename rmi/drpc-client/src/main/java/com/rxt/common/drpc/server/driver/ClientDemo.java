package com.rxt.common.drpc.server.driver;

import com.rxt.common.drpc.server.rpc.RPCClientProxy;
import com.rxt.common.drpc.server.service.IHello;
import com.rxt.common.zk.IServiceDiscovery;
import com.rxt.common.zk.ServiceDiscoeryImpl;
import com.rxt.common.zk.zkConfig;

import java.util.concurrent.CountDownLatch;

/**
 * 客户端程序入口
 * 测试要点：通过传入version参数确定来指定调用哪个版本的实现
 */
public class ClientDemo {
    public static void main(String[] args) {
        IServiceDiscovery serviceDiscovery = new ServiceDiscoeryImpl(zkConfig.CONNECTION_STR);

        RPCClientProxy rpcClientProxy = new RPCClientProxy(serviceDiscovery);

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {

//            String version = i%2==0?"2.0":"";    //随机选择调用哪个版本的实现
            String version = "";

            IHello hello = rpcClientProxy.clientProxy(IHello.class, version);
            System.out.println(hello.sayHello("world"));
        }


    }
}
