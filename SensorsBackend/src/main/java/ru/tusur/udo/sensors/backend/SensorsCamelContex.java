package ru.tusur.udo.sensors.backend;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.impl.DefaultCamelContext;

@Singleton
@Startup
public class SensorsCamelContex extends DefaultCamelContext {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Inject
	private SensorsRoutesBuilder srb; 

	@PostConstruct
	public void initSensorsCamelContex()  {
		logger.info("INIT CAMEL CONTEXT");
		try {
			this.addRoutes(srb);
			this.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
