package pt.adrz.hellorestlet.application;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class TmpApplication extends Application{
	
	public TmpApplication() {}
	
	public TmpApplication(Context context) {
		super(context);
	}
	
	@Override
	public Restlet createInboundRoot() {
		
		Router router = new Router(this.getContext());
		
		// routing here ...
		
		return router;
	}

}
