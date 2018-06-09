package ru.tusur.udo.sensors.backend;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

@Singleton
@Startup
public class SensorsCamelContex extends DefaultCamelContext {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Inject
	private SensorsRoutesBuilder srb; 
	
	@Resource(lookup="java:jboss/DefaultJMSConnectionFactory")
	ConnectionFactory connectionFactory;
	
	@PostConstruct
	public void initSensorsCamelContex()  {
		logger.info("INIT CAMEL CONTEXT");
		try {
			this.addRoutes(srb);
			//
			ActiveMQComponent amqc = ActiveMQComponent.activeMQComponent();
			amqc.setConnectionFactory(connectionFactory);
			this.addComponent("activemq", amqc);
			//
			this.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
