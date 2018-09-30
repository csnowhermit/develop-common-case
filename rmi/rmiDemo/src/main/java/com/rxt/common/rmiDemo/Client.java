/**
 * FileName: Client
 * Author:   Ren Xiaotian
 * Date:     2018/8/8 14:41
 */

package com.rxt.common.rmiDemo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IHelloService helloService = (IHelloService) Naming.lookup("rmi://127.0.0.1:1099/hello");    //从注册中心找到实例
        System.out.println(helloService.sayHello("world"));
    }
}
