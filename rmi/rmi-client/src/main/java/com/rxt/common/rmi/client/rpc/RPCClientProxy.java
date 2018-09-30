/**
 * FileName: RPCClientProxy
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:59
 */

package com.rxt.common.rmi.client.rpc;

import java.lang.reflect.Proxy;

public class RPCClientProxy {
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {

        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }
}
