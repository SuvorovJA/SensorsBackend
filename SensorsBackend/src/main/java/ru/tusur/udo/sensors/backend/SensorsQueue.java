package ru.tusur.udo.sensors.backend;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.camel.BeanInject;
import org.apache.camel.ProducerTemplate;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "/jms/queue/SENSORS") })
public class SensorsQueue implements MessageListener {
	private Logger logger = Logger.getLogger(getClass().getName());

	
	@Inject
	private SensorsCamelContex scc;
	
	private ProducerTemplate producerTemplate;
	
	@PostConstruct
	public void initSensorsQueue(){
		this.producerTemplate = this.scc.createProducerTemplate();
		this.producerTemplate.setDefaultEndpointUri("direct:SensorsMQ");
	}
	
	@Override
	public void onMessage(Message arg0) {
		TextMessage tm = null;
		if (arg0 instanceof TextMessage){
			tm = (TextMessage) arg0;
			try {
//				logger.info(tm.getText()); // заглушка
				this.producerTemplate.sendBody(tm.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
	}

}
