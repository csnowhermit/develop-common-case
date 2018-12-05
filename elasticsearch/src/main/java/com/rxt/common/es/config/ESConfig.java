/**
 * FileName: ESConfig
 * Date:     2018/12/3 20:41
 */

package com.rxt.common.es.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * ES配置信息
 */
@Component
@PropertySource("classpath:es-config.properties")
@Data
public class ESConfig {

    @Value("${es.cluster.name}")
    private String clusterName;

    @Value("${es.host.ip}")
    private String ip;

    @Value("${es.host.port}")
    private int port;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
