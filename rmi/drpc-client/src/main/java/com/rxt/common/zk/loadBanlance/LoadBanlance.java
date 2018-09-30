/**
 * FileName: LoadBanlance
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 13:02
 */

package com.rxt.common.zk.loadBanlance;

import java.util.List;

/**
 * 负载均衡总接口
 */
public interface LoadBanlance {

    String selectHost(List<String> repos);
}
