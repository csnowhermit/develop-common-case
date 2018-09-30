/**
 * FileName: IModifyService
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:41
 */

package com.rxt.common.mySpring.demo.service;

/**
 * 修改接口
 */
public interface IModifyService {
    public String add(String name,String addr);
    public String remove(Integer id);
}
