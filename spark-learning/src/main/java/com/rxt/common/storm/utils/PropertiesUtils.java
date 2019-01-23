package com.rxt.common.storm.utils;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件工具类
 */
public class PropertiesUtils {
    private static Properties config = null;
    private static String configFile = "storm-example.properties";

    public static void loadProperties(String fileName) {
        config = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
            config.load(inputStream);
        } catch (IOException e) {
            e.fillInStackTrace();
            System.out.println("加载 " + fileName + " 失败：" + e.getStackTrace());
        }
    }

    public static String getPropertyValue(String key) {
        String value = StringUtils.EMPTY;
        if (config == null) {
            loadProperties(configFile);
        }

        try {
            value = config.getProperty(key);
        } catch (Exception e) {
            System.out.println(String.format("提取 %s 属性值异常！", key) + e);
        }

        return value != null ? value.trim() : StringUtils.EMPTY;
    }

}
