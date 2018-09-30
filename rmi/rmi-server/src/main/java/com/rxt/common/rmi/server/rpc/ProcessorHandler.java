/**
 * FileName: ProcessorHandler
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:20
 */

package com.rxt.common.rmi.server.rpc;

import com.rxt.common.commonUnit.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 处理每次发来的请求
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;    //发布的服务

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    /**
     * 处理socket请求
     */
    @Override
    public void run() {
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());    //获取来自客户端的输入流
            RPCRequest request = (RPCRequest) inputStream.readObject();    //反序列化远程传输对象

            System.out.println("A request was received: " + request);

            Object result = invoke(request);    //通过反射调用
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(result);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过反射调用
     *
     * @param request
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object invoke(RPCRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Object[] args = request.getParameters();    //获取参数
        Class<?>[] types = new Class[args.length];    //获取参数的类型
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }

        Method method = service.getClass().getMethod(request.getMethodName(), types);

        return method.invoke(service, args);
    }
}
