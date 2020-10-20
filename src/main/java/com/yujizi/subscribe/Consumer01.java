package com.yujizi.subscribe;

import com.yujizi.point2point.MyMessageListener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ProjectName: activemq_abc
 * @Package: com.yujizi
 * @ClassName: Consumer
 * @Author: ychw
 * @Description:
 * @Date: 2020/10/14 17:19
 * @Version: 1.0
 */
public class Consumer01 {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination;//消息队列
        MessageConsumer messageConsumer = null;

        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("短信发送T");
            messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(new MyMessageListener());
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}


