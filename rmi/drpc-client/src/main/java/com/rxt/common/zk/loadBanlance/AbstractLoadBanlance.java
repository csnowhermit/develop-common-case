/**
 * FileName: AbstractLoadBanlance
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 13:03
 */

package com.rxt.common.zk.loadBanlance;

import java.util.List;

/**
 * 抽象类：相当于模板类，做一些前置的操作
 */
public abstract class AbstractLoadBanlance implements LoadBanlance{

    /**
     * 做一些前置的操作
     * @param repos
     * @return
     */
    @Override
    public String selectHost(List<String> repos) {
        if(repos == null || repos.size()==0){
            return null;
        }
        if(repos.size()==1){
            return repos.get(0);
        }else{
            return doSelect(repos);
        }
    }

    /**
     * 定义个模板类，去扩展负载的操作
     * @param repos
     * @return
     */
    protected abstract String doSelect(List<String> repos);
}
