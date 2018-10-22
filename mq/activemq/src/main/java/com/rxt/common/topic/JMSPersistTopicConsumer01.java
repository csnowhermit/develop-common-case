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
 * JMS topic：消息的持久化订阅：先创建消费者，再创建生产者
 */
public class JMSPersistTopicConsumer01 {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.117.101:61616");
        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("consumer-01");
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Topic destination = session.createTopic("myTopic");    //创建目的地
            MessageConsumer messageConsumer = session.createDurableSubscriber(destination, "consumer-01");    //创建消息的持久化订阅

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
