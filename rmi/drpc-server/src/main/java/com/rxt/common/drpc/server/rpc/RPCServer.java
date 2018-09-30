package com.rxt.common.drpc.server.rpc;

import com.rxt.common.anno.RPCAnnotation;
import com.rxt.common.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private IRegisterCenter iRegisterCenter;    //注册中心
    private String serviceAddress;    //服务的注册地址

    Map<String, Object> handlerMap = new HashMap<>();    //存放服务名称和服务对象之间的关系

    public RPCServer(IRegisterCenter iRegisterCenter, String serviceAddress) {
        this.iRegisterCenter = iRegisterCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     * @param services
     */
    public void bind(Object... services){
        for(Object service: services){
            RPCAnnotation rpcAnnotation = service.getClass().getAnnotation(RPCAnnotation.class);
            String serviceName = rpcAnnotation.value().getName();
            String version = rpcAnnotation.version();    //获取版本号

            if(version != null && !"".equals(version)){
                serviceName = serviceName + "-" + version;
            }

            handlerMap.put(serviceName, service);    //绑定serviceName和service
        }
        System.out.println(handlerMap);
    }

    public void publish() {
        ServerSocket serverSocket = null;

        try {
            String[] addrs = serviceAddress.split(":");
            int port = Integer.parseInt(addrs[1]);

            for(String interfaceName: handlerMap.keySet()){
                iRegisterCenter.register(interfaceName, serviceAddress);
                System.out.println("服务注册成功：" + interfaceName + " -> " + serviceAddress);
            }

            serverSocket = new ServerSocket(port);    //启动一个监听
            while(true){    //循环监听
                Socket socket = serverSocket.accept();    //监听服务
                executorService.execute(new ProcessorHandler(socket, handlerMap));    //通过线程池来处理请求
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
