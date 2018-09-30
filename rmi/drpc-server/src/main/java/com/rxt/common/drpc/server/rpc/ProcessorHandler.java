package com.rxt.common.drpc.server.rpc;

import com.rxt.common.commonUnit.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class ProcessorHandler implements Runnable {

    private Socket socket;
    Map<String, Object> handlerMap;


    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        //TODO 处理socket请求
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());    //获取客户端的输入流
            RPCRequest request = (RPCRequest) inputStream.readObject();    //反序列化远程传输对象RPCRequest
            Object result = invoke(request);    //通过反射调用

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());   //通过输出流将结果返回给客户端
            outputStream.writeObject(result);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
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

    private Object invoke(RPCRequest request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }

        String serviceName = request.getClassName();
        String version = request.getVersion();
        if(version != null && !"".equals(version)){
            serviceName = serviceName + "-" + version;
        }

        System.out.println("客户端请求的：" + serviceName);

        //在hanglerMap中，根据客户端请求的地址，去拿到相应的服务，通过反射发起调用
        Object service = handlerMap.get(serviceName);
        Method method = service.getClass().getMethod(request.getMethodName(), types);

        return method.invoke(service, args);
    }

}
















