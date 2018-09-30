/**
 * FileName: ClientDemo
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 17:00
 */

package com.rxt.common.rmi.client.driver;

import com.rxt.common.rmi.client.rpc.RPCClientProxy;
import com.rxt.common.rmi.client.service.IHello;

public class ClientDemo {
    public static void main(String[] args) {
        RPCClientProxy rpcClientProxy = new RPCClientProxy();

        IHello hello = rpcClientProxy.clientProxy(IHello.class, "localhost", 8888);
        System.out.println(hello.sayHello("world"));
    }
}
