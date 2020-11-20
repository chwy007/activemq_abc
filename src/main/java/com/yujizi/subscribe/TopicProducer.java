package com.yujizi.subscribe;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ProjectName: activemq_abc
 * @Package: com.yujizi
 * @ClassName: Producer
 * @Author: ychw
 * @Description:
 * @Date: 2020/10/14 13:10
 * @Version: 1.0
 */
public class TopicProducer {

    private static final String ACTIVEMQ_URL= "tcp://192.168.126.132:61616";
    private static final String QUEUE_NAME= "短信发送T";

    public static void main(String[] args) throws JMSException {


        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(QUEUE_NAME);
        MessageProducer  messageProducer= session.createProducer(topic);



        for(int i=0;i<10;i++){
                String txt="166****000"+i+"：验证码";
                Message message=session.createTextMessage(txt);
                messageProducer.send(message);

            }


        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("消息已发布到mq ***********************");




    }


}
