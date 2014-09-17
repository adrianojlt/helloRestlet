package pt.adrz.hellorestlet.application;

import org.restlet.Application;
import org.restlet.Restlet;

public class MailServerApplication extends Application {
	
	public MailServerApplication() {

		this.setName("RESTful Mail server application");
		this.setDescription("example application for 'restlet in action' book");
	}
	
	@Override
	public Restlet createInboundRoot() {

		Restlet restlet = new FirstRestlet();

		return restlet;
	}
}
