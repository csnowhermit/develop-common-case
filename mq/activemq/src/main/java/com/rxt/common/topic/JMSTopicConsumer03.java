/**
 * FileName: JMSQueueConsumer
 * Author:   Ren Xiaotian
 * Date:     2018/7/7 18:40
 */

package com.rxt.common.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布-订阅模式：多消费者接收消息
 * JMS topic消费者：先启动消费者，在启动生产者
 */
public class JMSTopicConsumer03 {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.117.101:61616");
        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("myTopic");    //创建目的地
            MessageConsumer messageConsumer = session.createConsumer(destination);    //创建消息的消费者

            TextMessage textMessage = (TextMessage) messageConsumer.receive();

            System.out.println(textMessage.getText());

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
