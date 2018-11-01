/**
 * FileName: Client
 * Author:   Ren Xiaotian
 * Date:     2018/11/1 21:40
 */

package com.rxt.common.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class Client {
    private static String SERVER_DEFAULT_IP = "127.0.0.1";    //默认的端口号
    private static int SERVER_DEFAULT_PORT = 7777;    //默认的端口号

    public static void send(String expression) {
        send(SERVER_DEFAULT_PORT, expression);
    }

    private static void send(int port, String expression) {
//        log.info("算数表达式：{}", expression);
        System.out.println("算数表达式：" + expression);

        Socket socket = null;
        BufferedReader br = null;
        PrintWriter printWriter = null;

        try {
            socket = new Socket(SERVER_DEFAULT_IP, port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);    //自动刷新
            printWriter.println(expression);
//            log.info("__结果为：{}", br.readLine());
            System.out.println("__结果为：" + br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }

                // 便于gc
                br = null;
                printWriter = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
