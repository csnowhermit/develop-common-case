package com.rxt.common.drpc.server.rpc;

import com.rxt.common.commonUnit.RPCRequest;
import com.rxt.common.zk.IServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {
    private IServiceDiscovery serviceDiscovery;
    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RPCRequest rpcRequest = new RPCRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        rpcRequest.setVersion(this.version);

        String serviceAddress = serviceDiscovery.discover(rpcRequest.getClassName());    //根据接口名称拿到服务地址

        //通过TCP传输
        TCPTransport tcpTransport = new TCPTransport(serviceAddress);
        Object object  = tcpTransport.send(rpcRequest);    //发送请求

        return object;
    }
}
