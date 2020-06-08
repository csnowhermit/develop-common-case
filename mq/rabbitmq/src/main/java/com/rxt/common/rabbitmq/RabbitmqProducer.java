package com.rxt.common.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 生产者
 */
public class RabbitmqProducer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");    //连接ip
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");    // 虚拟机
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        Connection connection = connectionFactory.newConnection();    // 建立连接
        Channel channel = connection.createChannel();    // 创建消息通道

        String msg = "Hello RabbitMQ";
        channel.basicPublish(EXCHANGE_NAME, "rabbitmq.simple", null, msg.getBytes("UTF-8"));

        channel.close();
        connection.close();
    }
}
