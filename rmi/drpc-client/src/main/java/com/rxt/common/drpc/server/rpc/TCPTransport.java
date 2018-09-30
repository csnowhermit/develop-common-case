package com.rxt.common.drpc.server.rpc;

import com.rxt.common.commonUnit.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 网络传输层
 */
public class TCPTransport {
    private String serviceAddress;

    public TCPTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    private Socket newSocket(){
        System.out.println("创建一个连接");
        Socket socket  = null;

        try {

            String[] arrs = this.serviceAddress.split(":");
            String host = arrs[0];
            int port = Integer.parseInt(arrs[1]);

            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException("连接建立失败");
        }
        return socket;
    }


    public Object send(RPCRequest rpcRequest) throws IOException {
        Socket socket = null;
        try{
            socket = newSocket();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();

            inputStream.close();
            outputStream.close();

            return result;
        }catch(Exception e) {
            throw new RuntimeException("发起远程调用异常， " + e);
        }finally {
            if (socket!=null){
                socket.close();
            }
        }
    }
}
