package com.rxt.common.drpc.server.service.impl;

import com.rxt.common.anno.RPCAnnotation;
import com.rxt.common.drpc.server.service.IHello;

@RPCAnnotation(value = IHello.class, version = "2.0")
public class IHelloImpl2 implements IHello {
    @Override
    public String sayHello(String msg) {
        return "[version 2.0] hello " + msg;
    }
}
