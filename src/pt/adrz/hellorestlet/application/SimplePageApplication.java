package pt.adrz.hellorestlet.application;


//import org.apache.catalina.connector.Request;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pt.adrz.hellorestlet.resource.FirstRestlet;
import pt.adrz.hellorestlet.resource.HelloWorld;
import pt.adrz.hellorestlet.resource.MainPageRestlet;
import pt.adrz.hellorestlet.resource.RootResource;
import pt.adrz.hellorestlet.resource.TodoResource;
import pt.adrz.hellorestlet.resource.TodoResources;

public class SimplePageApplication extends Application {
	
	public SimplePageApplication() {
		
	}
	
	public SimplePageApplication(Context parentContext) {
		super(parentContext);
	}
	
	@Override
	public synchronized Restlet createInboundRoot() {
		
		Router router = new Router(getContext());

		//router.attach("/", new MainPageRestlet());
		router.attach("http://localhost:8111/", new MainPageRestlet());
		router.attach("http://localhost:8111/root", RootResource.class);
		router.attach("http://localhost:8111/firstrestlet", new FirstRestlet());
		router.attach("http://localhost:8111/mainpage", new MainPageRestlet());
		router.attach("http://localhost:8111/helloworld", HelloWorld.class);
		//router.attach("http://localhost:8111/firstresource", FirstResource.class);
		
		// Todo app ...
		router.attach("http://localhost:8111/rest/todos", 			TodoResources.class);
		router.attach("http://localhost:8111/rest/todos/{todoId}", 	TodoResource.class);
		
		// manage users
		
		return router;
	}
}
