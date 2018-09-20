package com.sound.config;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfiguration {

	@Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
	
	@Bean
	public Queue que() {
		return new ActiveMQQueue("sample.queue");
	}
	@Bean
	public Topic top() {
		return new ActiveMQTopic("sample.topic");
	}
	
	@Bean
	public RedeliveryPolicy redeliveryPolicy(){
	        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
	        //是否在每次尝试重新发送失败后,增长这个等待时间
	        redeliveryPolicy.setUseExponentialBackOff(true);
	        //重发次数,默认为6次   这里设置为10次
	        redeliveryPolicy.setMaximumRedeliveries(10);
	        //重发时间间隔,默认为1秒
	        redeliveryPolicy.setInitialRedeliveryDelay(1);
	        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
	        redeliveryPolicy.setBackOffMultiplier(2);
	        //是否避免消息碰撞
	        redeliveryPolicy.setUseCollisionAvoidance(false);
	        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
	        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
	        return redeliveryPolicy;
	}
	
	  


	@Bean
    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
       
        topicListenerContainer.setPubSubDomain(true);
        topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        //设置连接数
        topicListenerContainer.setConcurrency("10");
        //重连间隔时间
        topicListenerContainer.setRecoveryInterval(1000L);
        topicListenerContainer.setSessionAcknowledgeMode(4);
        return topicListenerContainer;
    }
	
	

}
