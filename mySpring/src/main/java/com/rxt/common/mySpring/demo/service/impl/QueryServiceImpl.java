/**
 * FileName: QueryServiceImpl
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:44
 */

package com.rxt.common.mySpring.demo.service.impl;

import com.rxt.common.mySpring.demo.service.IQueryService;
import com.rxt.common.mySpring.framework.annotation.MyService;

@MyService
public class QueryServiceImpl implements IQueryService {

    @Override
    public String search(String name) {
        return "invork search name = " + name;
    }

}
