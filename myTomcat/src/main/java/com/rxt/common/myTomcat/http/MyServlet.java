/**
 * FileName: MyServlet
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 13:32
 */

package com.rxt.common.myTomcat.http;

public abstract class MyServlet {
    public void service(MyRequest request,MyResponse response) throws Exception{
        //如果客户端发送的是GET请求，就调用doGet方法
        //如果是POST,调用doPost方法
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else{
            doPost(request, response);
        }
    }

    public abstract void doGet(MyRequest request,MyResponse response) throws Exception;

    public abstract void doPost(MyRequest request,MyResponse response) throws Exception;

}

