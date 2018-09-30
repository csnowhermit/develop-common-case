/**
 * FileName: ServerDemo
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:43
 */

package com.rxt.common.rmi.server.driver;

import com.rxt.common.rmi.server.rpc.RPCServer;
import com.rxt.common.rmi.server.service.IHello;
import com.rxt.common.rmi.server.service.impl.IHelloImpl;

/**
 * 服务端启动入口
 */
public class ServerDemo {
    public static void main(String[] args) {
        IHello hello = new IHelloImpl();
        RPCServer rpcServer = new RPCServer();

        rpcServer.publish(hello, 8888);    //发布服务，在8888端口
        System.out.println("finished");
    }
}
