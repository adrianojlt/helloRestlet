package pt.adrz.hellorestlet.main;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;
import org.restlet.routing.VirtualHost;

public class Main {
	
	public Main() { }

	public static void main(String[] args) throws Exception { 

    	Component c = new Component();

    	c.getServers().add(Protocol.HTTP,9000);
    	c.getClients().add(Protocol.CLAP);

    	VirtualHost host = c.getDefaultHost();
    	
    	// Applications
    	AppTodo appTodo = new AppTodo();
    	AppTest appTest = new AppTest();
    	AppUserManager appUserManagager = new AppUserManager();
    	Application appSimp = new Application();

    	appSimp.setInboundRoot(new Restlet() {
    		@Override
			public void handle(Request request, Response response) {
				response.setEntity("<html>simpleExample!!!</html>", MediaType.TEXT_HTML);
			}
		});
    	
    	// attach
    	host.attach("/todo",appTodo);
    	host.attach("/mang",appUserManagager);
    	host.attach("/test",appTest);
    	host.attach("/simp",appSimp);
    	
    	c.setDefaultHost(host);
    	c.start();
		
		//Example example = new Example();
		//try { example.simplePageApp(); standAloneClient(); }
		//catch (Exception e) { }
	}
	
	public static void standAloneClient() throws Exception {
		ClientResource client = new ClientResource("http://www.google.com/");
		client.get().write(System.out);
	}
	
	public static void tmp() { }
}
