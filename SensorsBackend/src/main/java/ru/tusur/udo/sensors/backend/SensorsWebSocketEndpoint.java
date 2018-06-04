package ru.tusur.udo.sensors.backend;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/sensors")
public class SensorsWebSocketEndpoint {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Inject
	private SensorsSessionsService sensorsSessionsService;

	@OnOpen
	public void onOpenSession(Session session) throws IOException { // любое
																	// название
																	// метода
		logger.info("WEBSOCKET SESSION IS OPEN");
//		session.getBasicRemote().sendText("hello from socket!");
		this.sensorsSessionsService.addSession(session);
	}

	@OnClose
	public void onCloseSession(Session session) {
		logger.info("WEBSOCKET SESSION IS CLOSE");
		this.sensorsSessionsService.removeSession(session);
	}

}
