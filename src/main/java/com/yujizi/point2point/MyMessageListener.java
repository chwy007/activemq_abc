package com.yujizi.point2point;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ProjectName: activemq_abc
 * @Package: com.yujizi
 * @ClassName: MyMessageListener
 * @Author: ychw
 * @Description:
 * @Date: 2020/10/14 17:55
 * @Version: 1.0
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
