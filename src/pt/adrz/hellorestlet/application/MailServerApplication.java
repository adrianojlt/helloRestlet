package pt.adrz.hellorestlet.application;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pt.adrz.hellorestlet.resource.RootResource;

public class MailServerApplication extends Application {
	
	public MailServerApplication() {
		setName("RESTful Mail Server");
		setDescription("Example for 'Restlet in Action' book");
		setOwner("Restlet S.A.S.");
		setAuthor("The Restlet Team");
	}
	
	@Override
	public Restlet createInboundRoot() {

		Router router = new Router(this.getContext());
		
		router.attach("http://localhost:8111/)",
				RootResource.class);
		
		return router;
	}
}
