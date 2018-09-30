package com.rxt.common.drpc.server.rpc;

import com.rxt.common.zk.IServiceDiscovery;

import java.lang.reflect.Proxy;

public class RPCClientProxy {

    private IServiceDiscovery serviceDiscovery;    //定义发现机制

    public RPCClientProxy(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    /**
     *
     * @param interfaceCls
     * @param <T>
     * @return
     */
    public <T> T clientProxy(final Class<T> interfaceCls, String version) {

        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls}, new RemoteInvocationHandler(serviceDiscovery, version));
    }

}
