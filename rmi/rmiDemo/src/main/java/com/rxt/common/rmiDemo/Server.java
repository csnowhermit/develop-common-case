/**
 * FileName: Server
 * Author:   Ren Xiaotian
 * Date:     2018/8/8 14:39
 */

package com.rxt.common.rmiDemo;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        IHelloService helloService = new IHelloServiceImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        Naming.bind("rmi://127.0.0.1/hello", helloService);
        System.out.println("服务发布成功");

    }
}
