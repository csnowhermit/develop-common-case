package com.rxt.common.cluster;

import com.rxt.common.drpc.server.service.IHello;
import com.rxt.common.anno.RPCAnnotation;

/**
 * 同一接口的同一实现：用方法的返回值区别部署在哪个节点上而已
 */
@RPCAnnotation(IHello.class)
public class IHelloImplNode2 implements IHello {
    @Override
    public String sayHello(String msg) {
        return "Node2-Hello " + msg;
    }
}
