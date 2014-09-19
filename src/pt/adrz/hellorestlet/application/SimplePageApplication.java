package pt.adrz.hellorestlet.application;


//import org.apache.catalina.connector.Request;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pt.adrz.hellorestlet.resource.FirstRestlet;
import pt.adrz.hellorestlet.resource.HelloWorld;
import pt.adrz.hellorestlet.resource.HowToRestlet;
import pt.adrz.hellorestlet.resource.MainPageRestlet;
import pt.adrz.hellorestlet.resource.RootResource;
import pt.adrz.hellorestlet.resource.TodoResource;
import pt.adrz.hellorestlet.resource.TodoResources;

public class SimplePageApplication extends Application {
	
	public SimplePageApplication() {
		super();
	}

	public SimplePageApplication(Context parentContext) {
		super(parentContext);
	}
	
	//public SimplePageApplication(Context parentContext) { super(parentContext); }
	
	@Override
	public synchronized Restlet createInboundRoot() {
		
		Router router = new Router(getContext());

		router.attach("/", new MainPageRestlet());
		router.attach("/root", RootResource.class);
		router.attach("/firstrestlet", new FirstRestlet());
		router.attach("/mainpage", new MainPageRestlet());
		router.attach("/helloworld", HelloWorld.class);

		//router.attach("http://localhost:8111/firstresource", FirstResource.class);
		
		// Todo app ...
		router.attach("/howto", new HowToRestlet());
		router.attach("/rest/todos", 			TodoResources.class);
		router.attach("/rest/todos/{todoId}", 	TodoResource.class);
		
		// manage users
		
		return router;
	}
}
