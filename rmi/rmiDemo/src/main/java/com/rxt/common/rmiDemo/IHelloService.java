/**
 * FileName: IHelloService
 * Author:   Ren Xiaotian
 * Date:     2018/8/8 14:36
 */

package com.rxt.common.rmiDemo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHelloService extends Remote {
    public String sayHello(String msg) throws RemoteException;
}
