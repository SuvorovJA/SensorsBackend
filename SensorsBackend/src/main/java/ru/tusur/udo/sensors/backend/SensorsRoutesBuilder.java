package ru.tusur.udo.sensors.backend;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

@Stateless
public class SensorsRoutesBuilder extends RouteBuilder {
	private Logger logger = Logger.getLogger(getClass().getName());

	@Inject
	private SensorsSessionsService sss;

	@Override
	public void configure() throws Exception {

		// заглушка
//		from("timer://timer?period=1000").process(new Processor() {
//			@Override
//			public void process(Exchange arg0) {
//				// System.out.println(arg0.getIn().getBody());
//				// logger.info("TEST MESSAGE");
//				arg0.getIn().setBody("TEST MESSAGE OVER CAMEL");
//			}
//		}).bean(this.sss);
		
		from("direct:SensorsMQ").bean(this.sss);

	}

}