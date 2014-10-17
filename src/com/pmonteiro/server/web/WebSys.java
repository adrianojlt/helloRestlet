package com.pmonteiro.server.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.pmonteiro.server.resource.SysTmpSR;

public class WebSys extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		
		
		Router sysRouter = new Router(this.getContext());
		
		sysRouter.attach("/", SysTmpSR.class);
		sysRouter.attach("/config", SysTmpSR.class);
		sysRouter.attach("/hello", SysTmpSR.class);

		return sysRouter;
	}
}
