/**
 * FileName: Server
 * Author:   Ren Xiaotian
 * Date:     2018/10/31 21:13
 */

package com.rxt.common.bio;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * bio Server端
 */
@Slf4j
public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);

    private static int DEFAULT_PORT = 7777;    //默认的端口号

    private static ServerSocket serverSocket;    //单例的ServerSocket

    private static ExecutorService service;    //线程池

    static {
        service = Executors.newFixedThreadPool(100);    //大小为100的线程池
    }

    public static void start() throws IOException {
        start(serverSocket);
    }

    public synchronized static void start(ServerSocket serverSocket) throws IOException {
        if (serverSocket != null) {
            return;
        }

        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
//            logger.info("服务端已启动，端口号：{}", DEFAULT_PORT);
            System.out.println("服务端已启动，端口号："+ DEFAULT_PORT);

            while (true) {
                Socket socket = serverSocket.accept();

                // 每来一个客户端的请求，就new一个Thread进行处理
//                new Thread(new ServerHandler(socket)).start();
                service.execute(new ServerHandler(socket));    //使用线程池代替new Thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();

            System.out.println("服务端已关闭");
            if (serverSocket != null) {
                serverSocket.close();
            }
            serverSocket = null;    //置空，便于gc
        }


//        log.info("服务端已启动，端口号为：{}", DEFAULT_PORT);

    }


}
