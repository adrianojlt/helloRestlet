package com.pmonteiro.server.web;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.restlet.util.RouteList;

import com.pmonteiro.fasttrial.resource.ClientServerResource;
import com.pmonteiro.fasttrial.resource.ClientsServerResource;
import com.pmonteiro.fasttrial.resource.ConcreteServerResource;
import com.pmonteiro.fasttrial.resource.UserServerResource;
import com.pmonteiro.fasttrial.resource.UsersServerResource;

public class WebApp extends Application {
	
	private ConfigFactory config;
	
	private Router drouter;
	private Router trouter;
	
	public WebApp() {
		
	}
	
	private void loadClasses() {
		
	}
	
	private void tmp() {
		
	}


	@Override
	public Restlet createInboundRoot() {
		
		this.tmp();

		this.loadConfig();
		this.loadClasses();
		
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
		//drouter.attach("/users",UsersServerResource.class);
		//drouter.attach("/{classname}",UsersServerResource.class);
		//drouter.attach("/{classname}",ClientsServerResource.class);
		drouter.attach("/{classname}",ConcreteServerResource.class);
		drouter.attach("/users/{userid}",UserServerResource.class);
		drouter.attach("/users/{userid}/clients",ClientsServerResource.class);
		drouter.attach("/users/{userid}/clients/",ClientsServerResource.class);
		drouter.attach("/users/{userid}/clients/{email}",ClientsServerResource.class);
		drouter.attach("/users/{userid}/clients/{email}/",ClientsServerResource.class);
		//drouter.attach("/users/{email}",UserServerResource.class);
		//drouter.attach("/users/{id}/clients",UserServerResource.class);
		//drouter.attach("/users/{email}/clients",UserServerResource.class);
		
		// clients
		drouter.attach("/clients",com.pmonteiro.fasttrial.resource.ClientsServerResource.class);
		drouter.attach("/clients/",ClientsServerResource.class);
		drouter.attach("/clients/id/{id}",ClientServerResource.class);
		drouter.attach("/clients/id/{id}/",ClientServerResource.class);
		drouter.attach("/clients/email/{email}",ClientServerResource.class);
		drouter.attach("/clients/email/{email}/",ClientServerResource.class);
		//RouteList list = new rout
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
