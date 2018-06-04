package ru.tusur.udo.sensors.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.websocket.Session;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@Stateless
public class SensorsSessionsService {
	private Logger logger = Logger.getLogger(getClass().getName());

	private List<Session> sessions = new ArrayList<Session>();

	@Handler
	public void toWS(Exchange arg0) {
		
		String socketMsg = arg0.getIn().getBody().toString();
		this.sessions.stream().forEach(session -> {
			if (session != null && session.isOpen()) {
				try {
					session.getBasicRemote().sendText(socketMsg);
					logger.info("toWS:" + arg0.getIn().getBody().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public void removeSession(Session session) {
		logger.info("SESSION REMOVE");
		this.sessions.remove(session);
	}

	public void addSession(Session session) {
		logger.info("SESSION ADDED");
		this.sessions.add(session);
	}

}
