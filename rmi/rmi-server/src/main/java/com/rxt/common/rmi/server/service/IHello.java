/**
 * FileName: IHello
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:13
 */

package com.rxt.common.rmi.server.service;

/**
 * 服务端对外暴露的接口
 */
public interface IHello {
    String sayHello(String msg);
}
