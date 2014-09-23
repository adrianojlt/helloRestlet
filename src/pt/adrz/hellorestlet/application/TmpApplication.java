package pt.adrz.hellorestlet.application;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

import pt.adrz.hellorestlet.filter.Blocker;
import pt.adrz.hellorestlet.resource.FirstResource;
import pt.adrz.hellorestlet.resource.FirstRestlet;
import pt.adrz.hellorestlet.resource.FirstServerResource;
import pt.adrz.hellorestlet.resource.Tracer;

public class TmpApplication extends Application{
	
	public TmpApplication() {}
	
	public TmpApplication(Context context) {
		super(context);
	}
	
	@Override
	public Restlet createInboundRoot() {
		
		Router router = new Router(this.getContext());

		Restlet restlet = new FirstRestlet();
		
		// routing here ...
		router.attach("http://localhost:8111/",FirstServerResource.class);
		//router.attach("",FirstServerResource.class);
		router.attach("http://localhost:8111/firstservlet", restlet);
		
		//return restlet;
		return router;
		//return this.testFilter(true);
	}
	
	private Restlet testFilter(boolean block) {
		Blocker blocker = new Blocker(this.getContext());
		if (block) blocker.getBlockedAddresses().add("localhost");
		blocker.setNext(new Tracer(this.getContext()));
		return blocker;
	}

}
