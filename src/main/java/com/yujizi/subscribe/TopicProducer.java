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

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL= ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        System.out.println(USERNAME);
        System.out.println(PASSWORD);
        System.out.println(BROKERURL);
        ConnectionFactory connectionFactory=null;
        Connection connection=null;
        Session session=null;
        Destination destination;//消息队列
        MessageProducer messageProducer=null;

        connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//加事务处理
            destination=session.createTopic("短信发送T");
            messageProducer=session.createProducer(destination);


            for(int i=0;i<10;i++){
                String txt="166****000"+i+"：验证码";
                Message message=session.createTextMessage(txt);
                messageProducer.send(message);

            }

            session.commit();


        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                messageProducer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }


    }


}
