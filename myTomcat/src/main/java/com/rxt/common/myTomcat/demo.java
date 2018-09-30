/**
 * FileName: demo
 * Author:   Ren Xiaotian
 * Date:     2018/9/29 15:58
 */

package com.rxt.common.myTomcat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 通过类读取资源文件 Demo
 */
public class demo {
    public static void main(String[] args) throws IOException {
//        System.out.println(new demo().getClass().getResource("").getPath());
//        System.out.println(demo.class.getClassLoader().getResource("/"));

        InputStream inputStream = demo.class.getClassLoader().getResourceAsStream("myTomcat.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        System.out.println(properties.get("servlet.one.url"));
        System.out.println(properties.get("servlet.one.className"));

        System.out.println(properties.get("servlet.two.url"));
        System.out.println(properties.get("servlet.two.className"));

    }
}
