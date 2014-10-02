package com.pmonteiro.server.web;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.pmonteiro.fasttrial.resource.ClientServerResource;
import com.pmonteiro.fasttrial.resource.ClientsServerResource;
import com.pmonteiro.fasttrial.resource.UserServerResource;
import com.pmonteiro.fasttrial.resource.UsersServerResource;

public class WebApp extends Application {
	
	private ConfigFactory config;
	
	private Router drouter;
	private Router trouter;
	
	public WebApp() {
		
	}


	@Override
	public Restlet createInboundRoot() {
		
		this.loadConfig();
		
		// filters 
		
		// routes
		attachDefaultRouter();

		return drouter;
	}


	@Override
	public Restlet createOutboundRoot() {
		return null;
	}
	
	private void loadConfig() {
		this.config = ConfigFactory.getConfigFactory(ConfigFactory.STORAGE_TYPE.STATIC);
	}
	
	private void attachDefaultRouter() {
		
		drouter = new Router(getContext());
		
		// users
		drouter.attach("/users",UsersServerResource.class);
		drouter.attach("/users/",UsersServerResource.class);
		drouter.attach("/users/{id}",UserServerResource.class);
		drouter.attach("/users/{id}/clients",ClientsServerResource.class);
		drouter.attach("/users/{id}/clients/",ClientsServerResource.class);
		//drouter.attach("/users/{email}",UserServerResource.class);
		//drouter.attach("/users/{id}/clients",UserServerResource.class);
		//drouter.attach("/users/{email}/clients",UserServerResource.class);
		
		// clients
		drouter.attach("/clients",ClientsServerResource.class);
		drouter.attach("/clients/",ClientsServerResource.class);
		drouter.attach("/clients/{id}",ClientServerResource.class);
		//drouter.attach("/clients/{email}",ClientServerResource.class);
		
		// groups
	}
	
	private Router createConfigRouter() {
		return null;
	}
	
	private Router createApiRouter() {
		return null;
	}
}
