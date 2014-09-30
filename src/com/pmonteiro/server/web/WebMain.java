package com.pmonteiro.server.web;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;

public class WebMain {
	
    public static void main(String[] args) throws Exception {
    	
    	Component c = new Component();

    	c.getServers().add(Protocol.HTTP,9000);
    	c.getClients().add(Protocol.CLAP);
    	
        //c.getLogService().setLogPropertiesRef("clap:///logging.properties");

    	VirtualHost host = c.getDefaultHost();

    	WebApp wp = new WebApp();
    	
    	host.attach("/v1", wp);
    	host.attachDefault(wp);

    	c.setDefaultHost(host);
    	
    	c.start();
    }
}
