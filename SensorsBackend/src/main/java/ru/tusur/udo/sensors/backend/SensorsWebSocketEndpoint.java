package ru.tusur.udo.sensors.backend;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/sensors")
public class SensorsWebSocketEndpoint {

	private Logger logger = Logger.getLogger(getClass().getName());
	@PostConstruct
	public void initWwbSocket(){
		logger.info("LOGGER from SensorsWebSocketEndpoint");
	}
	
}
