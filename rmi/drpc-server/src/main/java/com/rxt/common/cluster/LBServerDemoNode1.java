package com.rxt.common.cluster;

import com.rxt.common.drpc.server.service.IHello;
import com.rxt.common.drpc.server.rpc.RPCServer;
import com.rxt.common.zk.IRegisterCenter;
import com.rxt.common.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * rpc服务 集群化部署
 * 测试要点：同一接口同一实现，部署在不同机器上而已（通过函数的返回值来判断调用的是哪个服务器的部署）
 */
public class LBServerDemoNode1 {
    public static void main(String[] args) throws IOException {
        IHello hello = new IHelloImplNode1();

        IRegisterCenter registerCenter = new RegisterCenterImpl();

        RPCServer rpcServer = new RPCServer(registerCenter, "127.0.0.1:8080");
        rpcServer.bind(hello);
        rpcServer.publish();
        System.in.read();

    }
}
