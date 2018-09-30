/**
 * FileName: RemoteInvocationHandler
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:59
 */

package com.rxt.common.rmi.client.rpc;

import com.rxt.common.commonUnit.RPCRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //组装请求
        RPCRequest rpcRequest = new RPCRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        //通过TCP传输
        TCPTransport tcpTransport = new TCPTransport(this.host, this.port);
        Object object  = tcpTransport.send(rpcRequest);    //发送请求

        return object;
    }
}
