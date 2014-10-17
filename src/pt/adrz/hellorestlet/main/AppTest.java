package pt.adrz.hellorestlet.main;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pt.adrz.hellorestlet.resource.FirstRestlet;

public class AppTest extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		
		Router testRouter = new Router(this.getContext());
		Router innerRouter = new Router(this.getContext());
		innerRouter.attach("/a", new FirstRestlet());
		
		testRouter.attach("/hello}", innerRouter);
		//testRouter.attach("/hello}", new FirstRestlet());
		//todoRouter.attachDefault(TodosResource.class);

		return testRouter;
	}

}
