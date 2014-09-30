package com.pmonteiro.server.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class WebApp extends Application {
	
	private ConfigFactory config;
	
	public WebApp() {
		
	}


	@Override
	public Restlet createInboundRoot() {
		
		this.loadConfig();

		return null;
	}


	@Override
	public Restlet createOutboundRoot() {

		return null;
	}
	
	private void loadConfig() {
		this.config = ConfigFactory.getConfigFactory(ConfigFactory.STORAGE_TYPE.STATIC);
	}
	
	private Router createConfigRouter() {
		return null;
	}
	
	private Router createApiRouter() {
		return null;
	}
}
