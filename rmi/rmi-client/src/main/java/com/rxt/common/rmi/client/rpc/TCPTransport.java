/**
 * FileName: TCPTransport
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:56
 */

package com.rxt.common.rmi.client.rpc;

import com.rxt.common.commonUnit.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * 网络传输层
 */
public class TCPTransport {
    private String host;
    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        System.out.println("创建一个连接");
        Socket socket = null;

        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException("连接建立失败");
        }
        return socket;
    }

    /**
     * 向服务端发送数据，发起远程调用
     *
     * @param rpcRequest 调用数据封装成RPCRequest
     * @return
     * @throws IOException
     */
    public Object send(RPCRequest rpcRequest) throws IOException {
        Socket socket = null;
        try {
            socket = newSocket();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();

            inputStream.close();
            outputStream.close();

            return result;
        } catch (Exception e) {
            throw new RuntimeException("发起远程调用异常， " + e);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
