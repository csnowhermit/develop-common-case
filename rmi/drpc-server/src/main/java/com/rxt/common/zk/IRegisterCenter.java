/**
 * FileName: IRegisterCenter
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 11:13
 */

package com.rxt.common.zk;

/**
 * 注册中心
 */
public interface IRegisterCenter {
    void register(String serviceName, String serviceAddress);

}
