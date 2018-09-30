package com.rxt.common.drpc.server.service.impl;

import com.rxt.common.anno.RPCAnnotation;
import com.rxt.common.drpc.server.service.IHello;

@RPCAnnotation(IHello.class)
public class IHelloImpl implements IHello {
    @Override
    public String sayHello(String msg) {
        return "Hello " + msg;
    }
}
