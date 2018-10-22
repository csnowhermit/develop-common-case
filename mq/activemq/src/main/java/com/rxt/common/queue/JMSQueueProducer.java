/**
 * FileName: JMSQueueProducer
 * Author:   Ren Xiaotian
 * Date:     2018/10/22 16:03
 */

package com.rxt.common.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * JMS Queue生产者：需先启动生产者，再启动消费者
 */
public class JMSQueueProducer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.117.101:61616");

        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);    //事务，自动确认

            Destination destination = session.createQueue("MyQueue");    //创建目的地
            MessageProducer messageProducer = session.createProducer(destination);  //创建消息的生产者

            TextMessage textMessage = session.createTextMessage("Hello");    //创建发送者要发送的消息
            messageProducer.send(textMessage);    //发送消息

            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
