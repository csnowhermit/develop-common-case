/**
 * FileName: MyAction
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:38
 */

package com.rxt.common.mySpring.demo.mvc.action;

import com.rxt.common.mySpring.demo.service.IModifyService;
import com.rxt.common.mySpring.demo.service.IQueryService;
import com.rxt.common.mySpring.framework.annotation.MyAutowired;
import com.rxt.common.mySpring.framework.annotation.MyRequestMapping;
import com.rxt.common.mySpring.framework.annotation.MyController;
import com.rxt.common.mySpring.framework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MyController
@MyRequestMapping("/web")
public class MyAction {
    @MyAutowired
    private IQueryService queryService;
    @MyAutowired("aa")
    private IModifyService modifyService;

    @MyRequestMapping("/search/*.json")
    public void search(HttpServletRequest request, HttpServletResponse response,
                       @MyRequestParam("name") String name) {
        String result = queryService.search(name);
        out(response, result);
    }


    @MyRequestMapping("/add.json")
    public void add(HttpServletRequest request, HttpServletResponse response,
                    @MyRequestParam("name") String name,
                    @MyRequestParam("addr") String addr) {
        String result = modifyService.add(name, addr);
        out(response, result);
    }


    @MyRequestMapping("/remove.json")
    public void remove(HttpServletRequest request, HttpServletResponse response,
                       @MyRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        out(response, result);
    }

    /**
     * 将输出数据写回到浏览器
     *
     * @param response
     * @param str
     */
    private void out(HttpServletResponse response, String str) {
        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
