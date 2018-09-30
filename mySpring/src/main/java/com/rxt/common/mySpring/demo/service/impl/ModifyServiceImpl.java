/**
 * FileName: ModifyServiceImpl
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:47
 */

package com.rxt.common.mySpring.demo.service.impl;

import com.rxt.common.mySpring.demo.service.IModifyService;
import com.rxt.common.mySpring.framework.annotation.MyService;

@MyService("aa")
public class ModifyServiceImpl implements IModifyService {

    @Override
    public String add(String name, String addr) {
        return "invork add name = " + name + ",addr=" + addr;
    }

    @Override
    public String remove(Integer id) {
        return "invork remove id = " + id;
    }

}
