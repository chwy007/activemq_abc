package com.yujizi.point2point;

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
public class Consumer {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL= ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory=null;
        Connection connection=null;
        Session session=null;
        Destination destination;//消息队列
        MessageConsumer messageConsumer=null;

        connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination=session.createQueue("短信发送");
            messageConsumer=session.createConsumer(destination);

            for (int i=0;i<1;i++){
                TextMessage textMessage= (TextMessage) messageConsumer.receive();
                System.out.println(textMessage.getText());
            }
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                messageConsumer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }




}
