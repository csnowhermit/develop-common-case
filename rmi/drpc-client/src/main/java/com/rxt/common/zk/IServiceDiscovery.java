/**
 * FileName: IServiceDiscovery
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 12:44
 */

package com.rxt.common.zk;

/**
 * 发现服务
 */
public interface IServiceDiscovery {

    /**
     * 根据服务名获得服务的调用地址
     * @param serviceName
     * @return
     */
    String discover(String serviceName);
}
