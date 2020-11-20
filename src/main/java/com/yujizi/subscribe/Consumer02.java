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
public class Consumer02 {
    private static final String ACTIVEMQ_URL= "tcp://192.168.126.132:61616";
    private static final String QUEUE_NAME= "短信发送T";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(topic);
        messageConsumer.setMessageListener(new MyMessageListener());

    }






}


