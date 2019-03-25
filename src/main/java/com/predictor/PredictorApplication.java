package com.predictor;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

import static java.util.EnumSet.of;
import static javax.servlet.DispatcherType.REQUEST;

public class PredictorApplication {


	private static final Logger LOGGER = LoggerFactory.getLogger(PredictorApplication.class);
	private static final String APP_PATH_NAME = "/predictor-system/*";
	private static final int DEFAULT_PORT = 8081;

	public static void main(String[] args) {
		LOGGER.info("Starting application ...");
		try{
			//PropertyConfigurator.configure("classpath:log4j.properties");
			final DispatcherServlet dispatcherServlet = new DispatcherServlet();
			dispatcherServlet.setContextConfigLocation("classpath:application-context.xml");

			final WebAppContext handler = new WebAppContext();
			handler.addServlet(new ServletHolder(dispatcherServlet), APP_PATH_NAME);
			handler.setResourceBase("src/main/resources");
			final Server server = new Server(DEFAULT_PORT);
			server.setHandler(handler);

			server.start();
			LOGGER.info("Application started...");
			server.join();
			LOGGER.info("Application was shutdown!");

		}catch (Exception e){
			LOGGER.error("A error happened");
		}
	}

}
