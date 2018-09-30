/**
 * FileName: IHelloServiceImpl
 * Author:   Ren Xiaotian
 * Date:     2018/8/8 14:37
 */

package com.rxt.common.rmiDemo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 实现IHelloService接口
 */
public class IHelloServiceImpl extends UnicastRemoteObject implements IHelloService {
    protected IHelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "Hello " + msg;
    }
}
