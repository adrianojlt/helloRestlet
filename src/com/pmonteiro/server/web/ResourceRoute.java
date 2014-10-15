package com.pmonteiro.server.web;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.restlet.routing.TemplateRoute;

public class ResourceRoute extends TemplateRoute {
	
	private Class serverResource;
	
	public ResourceRoute(Router router, String path, Class serverResource, Application app) {
		super(router,path,null);
		//Application app = this.getApplication();
		this.serverResource = serverResource;
		this.setNext(app.createFinder(serverResource));
	}
	
	//@Override public float score(Request request, Response response) { return 0; }
}
