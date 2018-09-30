/**
 * FileName: RPCServer
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:16
 */

package com.rxt.common.rmi.server.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RPC 服务端
 */
public class RPCServer {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 发布服务
     *
     * @param service 要发布的服务
     * @param port    服务发布的端口
     */
    public void publish(final Object service, int port) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);    //启动一个监听
            while (true) {    //循环监听
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket, service));    //通过线程吃来请求处理
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
