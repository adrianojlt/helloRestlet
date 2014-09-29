package com.pmonteiro.server.web;

import java.util.ArrayList;
import java.util.List;

public class ConfigFromStatic extends ConfigFactory {
	
	private List<Route> routes; 
	
	/**
	 * load all class names from the package pt.pmonteiro.X.api.* 
	 */
	public ConfigFromStatic() {
		
		routes = new ArrayList<Route>();
		routes.add(new Route("/rest/user","pt.pmonteiro.fastrial.api","User"));
		routes.add(new Route("/rest/group","pt.pmonteiro.fastrial.api","Group"));
		routes.add(new Route("/rest/client","pt.pmonteiro.fastrial.api","Client"));
	}

	@Override
	public List<Route> getRoutes() {
		return this.routes;
	}

}
