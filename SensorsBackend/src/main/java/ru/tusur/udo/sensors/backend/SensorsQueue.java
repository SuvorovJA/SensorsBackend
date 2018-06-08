package ru.tusur.udo.sensors.backend;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "/jms/queue/SENSORS") })
public class SensorsQueue implements MessageListener {
	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void onMessage(Message arg0) {
		TextMessage tm = null;
		if (arg0 instanceof TextMessage){
			tm = (TextMessage) arg0;
			try {
				logger.info(tm.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
