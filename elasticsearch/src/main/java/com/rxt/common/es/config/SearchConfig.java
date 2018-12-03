/**
 * FileName: SearchConfig
 * Author:   Ren Xiaotian
 * Date:     2018/12/3 21:05
 */

package com.rxt.common.es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class SearchConfig {

    //    @Autowired EsConfig esConfig;
    @Autowired
    ESConfig esConfig;

    @Bean
    public TransportClient client() throws UnknownHostException {
        TransportAddress node = new TransportAddress(
                InetAddress.getByName(esConfig.getIP()),
                esConfig.getPort()
        );

        Settings settings = Settings.builder()
                .put("cluster.name", esConfig.getClusterName())
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);

        return client;
    }

}
