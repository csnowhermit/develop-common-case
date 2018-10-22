/**
 * FileName: JMSTopicProducer
 * Author:   Ren Xiaotian
 * Date:     2018/10/22 16:28
 */

package com.rxt.common.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * JMS topic生产者：先启动消费者，在启动生产者
 */
public class JMSTopicProducer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.117.101:61616");
        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);    //Session.AUTO_ACKNOWLEDGE表示自动提交

            Destination destination = session.createTopic("myTopic");    //创建目的地
            MessageProducer messageProducer = session.createProducer(destination);    //创建消息的生产者
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);    //设为持久化订阅方式

            TextMessage textMessage =session.createTextMessage("VIP 的上课时间是：周三、周六、周日");    //创建发送者需要发送的消息

            messageProducer.send(textMessage);

            session.commit();    //意味着消息被确认
//            session.rollback();    //意味着消费端的所有消息被恢复，重新提交
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
