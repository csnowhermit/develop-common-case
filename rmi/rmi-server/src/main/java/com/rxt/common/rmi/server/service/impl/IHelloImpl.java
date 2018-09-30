/**
 * FileName: IHelloImpl
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:13
 */

package com.rxt.common.rmi.server.service.impl;

import com.rxt.common.rmi.server.service.IHello;

/**
 * 服务端对暴露出的接口的实现
 */
public class IHelloImpl implements IHello {
    @Override
    public String sayHello(String msg) {
        return "Hello " + msg;
    }
}
