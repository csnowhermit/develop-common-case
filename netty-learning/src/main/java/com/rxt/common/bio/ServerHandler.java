/**
 * FileName: ServerHandler
 * Author:   Ren Xiaotian
 * Date:     2018/11/1 20:41
 */

package com.rxt.common.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 处理接收完客户端的请求之后的处理
 */
@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter printWriter = null;

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);    //自动刷新

            String expression;
            String result;
            while ((expression = br.readLine()) == null) {
                break;
            }

            System.out.println("服务端收到信息：" + expression);

            result = Calcutor.cal(expression);

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
