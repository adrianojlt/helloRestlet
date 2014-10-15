package com.pmonteiro.server.web;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;

public class WebMain {
	
    public static void main(String[] args) throws Exception {
    	
    	Component c = new Component();

    	c.getServers().add(Protocol.HTTP,9000);
    	c.getClients().add(Protocol.CLAP);
    	
    	
        //c.getLogService().setLogPropertiesRef("clap:///logging.properties");
        c.getLogService().setLogPropertiesRef("clap://class/com/pmonteiro/server/config/logging.properties");

    	VirtualHost host = c.getDefaultHost();

    	WebApp wp = new WebApp();
    	
    	host.attach("/v1", wp);

    	host.attachDefault(wp);

    	c.setDefaultHost(host);
    	
    	c.start();
    }
}
