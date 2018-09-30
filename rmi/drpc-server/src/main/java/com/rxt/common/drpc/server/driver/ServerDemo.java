package com.rxt.common.drpc.server.driver;

import com.rxt.common.drpc.server.rpc.RPCServer;
import com.rxt.common.drpc.server.service.IHello;
import com.rxt.common.drpc.server.service.impl.IHelloImpl;
import com.rxt.common.drpc.server.service.impl.IHelloImpl2;
import com.rxt.common.zk.IRegisterCenter;
import com.rxt.common.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * 使用zk作注册中心，单机部署
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        IHello hello = new IHelloImpl();
        IHello hello2 = new IHelloImpl2();

        IRegisterCenter registerCenter = new RegisterCenterImpl();

        RPCServer rpcServer = new RPCServer(registerCenter, "127.0.0.1:8080");
        rpcServer.bind(hello, hello2);
        rpcServer.publish();
        System.in.read();

    }
}
