/**
 * FileName: Myresponse
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 13:31
 */

package com.rxt.common.myTomcat.http;

import java.io.OutputStream;

public class MyResponse {

    private OutputStream os;

    public MyResponse(OutputStream os){
        this.os = os;
    }

    public void write(String outstr) throws Exception{

        os.write(outstr.getBytes());
        os.flush();
    }
}

