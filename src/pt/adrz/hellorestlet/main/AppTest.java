package pt.adrz.hellorestlet.main;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.pmonteiro.fasttrial.resource.ClientServerResource;
import com.pmonteiro.fasttrial.resource.ClientsServerResource;
import com.pmonteiro.fasttrial.resource.TestServerResource;
import com.pmonteiro.fasttrial.resource.UserServerResource;
import com.pmonteiro.fasttrial.resource.UsersServerResource;

public class AppTest extends Application{
	
	@Override
	public Restlet createInboundRoot() {
		
		Router testRouter = new Router();
		
		//testRouter.attach(base + "/{test}",TestServerResource.class);
		//testRouter.attach(base + "/{classname}",ConcreteServerResource.class);
		testRouter.attach("/db4o",TestServerResource.class);
		
		// users
		testRouter.attach("/users",UsersServerResource.class);
		testRouter.attach("/users/",UsersServerResource.class);
		testRouter.attach("/users/{userid}",UserServerResource.class);
		testRouter.attach("/users/{userid}/clients",ClientsServerResource.class);
		testRouter.attach("/users/{userid}/clients/",ClientsServerResource.class);
		testRouter.attach("/users/{userid}/clients/{email}",ClientsServerResource.class);
		testRouter.attach("/users/{userid}/clients/{email}/",ClientsServerResource.class);
		//drouter.attach(base + "/users/{email}",UserServerResource.class);
		//drouter.attach(base + "/users/{id}/clients",UserServerResource.class);
		//drouter.attach(base + "/users/{email}/clients",UserServerResource.class);
		
		// clients
		testRouter.attach("/clients",com.pmonteiro.fasttrial.resource.ClientsServerResource.class);
		testRouter.attach("/clients/",ClientsServerResource.class);
		testRouter.attach("/clients/{id}",ClientServerResource.class);
		testRouter.attach("/clients/{id}/",ClientServerResource.class);
		testRouter.attach("/clients/email/{email}",ClientServerResource.class);
		testRouter.attach("/clients/email/{email}/",ClientServerResource.class);
		//RouteList list = new rout
		//drouter.attach("/clients/{email}",ClientServerResource.class);
		
		testRouter.attachDefault(TestServerResource.class);
		//drouter.attach("/{classname}",UsersServerResource.class);
		//drouter.attach("/{classname}",ClientsServerResource.class);
		
		return testRouter;
	}
}
