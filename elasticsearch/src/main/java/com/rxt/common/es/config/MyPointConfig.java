/**
 * FileName: MyPointConfig
 * Author:   Ren Xiaotian
 * Date:     2018/12/3 21:02
 */

package com.rxt.common.es.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 我的位置
 */
@Component
@PropertySource("classpath:mypoint.properties")
@Data
public class MyPointConfig {

    @Value("${my.point.lon}")
    private double lon;    //经度

    @Value("${my.point.lat}")
    private double lat;    //纬度

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
