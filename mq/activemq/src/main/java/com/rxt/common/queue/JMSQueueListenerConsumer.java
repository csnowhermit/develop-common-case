/**
 * FileName: JMSQueueListenerConsumer
 * Author:   Ren Xiaotian
 * Date:     2018/10/22 16:38
 */

package com.rxt.common.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * JMS queue：消息的监听式接收到
 */
public class JMSQueueListenerConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.117.101:61616");
        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("MyQueue");    //创建目的地
            MessageConsumer messageConsumer = session.createConsumer(destination);    //创建消息的消费者

            MessageListener messageListener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println(((TextMessage)message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            };

            while(true){    //通过while循环阻塞消息的接收
                messageConsumer.setMessageListener(messageListener);
                session.commit();
            }

//            session.close();
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
