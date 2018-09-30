/**
 * FileName: FirstServlet
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 13:33
 */

package com.rxt.common.myTomcat.servlet;

import com.rxt.common.myTomcat.http.MyRequest;
import com.rxt.common.myTomcat.http.MyResponse;
import com.rxt.common.myTomcat.http.MyServlet;

public class FirstServlet extends MyServlet {

    @Override
    public void doGet(MyRequest request, MyResponse response) throws Exception{
        this.doPost(request, response);
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws Exception {

        //在这里处理自己所有的逻辑
        response.write("Hello MyTomcat " + ",method= " + request.getMethod() + ",url=" + request.getUrl());

    }

}

