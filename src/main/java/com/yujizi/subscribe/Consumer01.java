package com.yujizi.subscribe;

import com.yujizi.point2point.MyMessageListener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.usage.StoreUsage;

import javax.jms.*;
import java.io.IOException;
import java.sql.SQLOutput;

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
    private static final String ACTIVEMQ_URL= "tcp://192.168.126.132:61616";
    private static final String QUEUE_NAME= "短信发送T";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("z4");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(QUEUE_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "张三");
        connection.start();

        Message message = topicSubscriber.receive();
        while (message!=null){
            TextMessage textMessage=(TextMessage)message;
            System.out.println(textMessage.getText());
            message=topicSubscriber.receive(5000l);
        }
        System.out.println("监听结束********************");
        topicSubscriber.close();
        session.close();
        connection.close();




    }


}


