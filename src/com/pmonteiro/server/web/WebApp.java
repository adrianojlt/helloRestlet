package com.pmonteiro.server.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MemoryRealm;
import org.restlet.security.MethodAuthorizer;
import org.restlet.security.Realm;
import org.restlet.security.Role;
import org.restlet.security.RoleAuthorizer;

import pt.adrz.hellorestlet.resource.TodoResource;
import pt.adrz.hellorestlet.resource.TodosResource;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmonteiro.fasttrial.model.accounts.UserAccount;
import com.pmonteiro.fasttrial.model.accounts.UserType;
import com.pmonteiro.fasttrial.model.test.User;
import com.pmonteiro.fasttrial.resource.ClientServerResource;
import com.pmonteiro.fasttrial.resource.ClientsServerResource;
import com.pmonteiro.fasttrial.resource.ConcreteServerResource;
import com.pmonteiro.fasttrial.resource.TestServerResource;
import com.pmonteiro.fasttrial.resource.UserServerResource;
import com.pmonteiro.fasttrial.resource.UsersServerResource;
import com.pmonteiro.fasttrial.resource.accounts.UserAccountServerResource;
import com.pmonteiro.fasttrial.resource.accounts.UsersAccountServerResource;

public class WebApp extends Application {
	
	private ConfigFactory config;

		
	ChallengeAuthenticator apiGuardFtRouter;
	
	private Router ftRouter;
	private Router tutorialRouter;
	private Router testRouter;
	private Router todoRouter;
	
	public WebApp() {

		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role(this, "root"));
		roles.add(new Role(this, "badmin"));
		roles.add(new Role(this, "tadmin"));
		roles.add(new Role(this, "user"));
		setRoles(roles);
	}
	
	private void loadClasses() { }
	
	private void tmp() { }
	
	private void db4oStore() {
		
		ObjectContainer db = null;
		
		//new File("users.data").delete();
		db = Db4o.openFile("users.data");
		
		User user = new User();
		user.setName("john");
		//db.set(user);
		db.delete(user);
		db.commit();
		db.close();
	}
	
	private void loadDB4O() {

		ObjectContainer db = Db4o.openFile("users.data");
		
		User user = new User();
		user.setName("john");
		
		ObjectSet os = db.get(user);
		while (os.hasNext()) System.out.println(os.next());
	}

	@Override
	public Restlet createInboundRoot() {
		
		//this.db4oStore();
		//this.loadDB4O();

		this.loadConfig();
		this.loadClasses();
		
		// security
		
		// filters 
		
		// routes
		attachFastTrialRouter();
		attachTutorialRouter();
		attachTestRouter();
		attachTodoRouter();

		// chain ...
		ftRouter.attachDefault(tutorialRouter);
		tutorialRouter.attachDefault(testRouter);
		testRouter.attachDefault(todoRouter);

		return ftRouter;
	}


	@Override
	public Restlet createOutboundRoot() {
		return null;
	}
	
	private void loadConfig() {
		this.config = ConfigFactory.getConfigFactory(ConfigFactory.STORAGE_TYPE.STATIC);
	}
	
	private void attachTestRouter() {

		String base = "/test";

		testRouter = new Router();
		
		//testRouter.attach(base + "/{test}",TestServerResource.class);
		//testRouter.attach(base + "/{classname}",ConcreteServerResource.class);
		testRouter.attach(base + "/db4o",TestServerResource.class);
		
		// users
		testRouter.attach(base + "/users",UsersServerResource.class);
		testRouter.attach(base + "/users/",UsersServerResource.class);
		testRouter.attach(base + "/users/{userid}",UserServerResource.class);
		testRouter.attach(base + "/users/{userid}/clients",ClientsServerResource.class);
		testRouter.attach(base + "/users/{userid}/clients/",ClientsServerResource.class);
		testRouter.attach(base + "/users/{userid}/clients/{email}",ClientsServerResource.class);
		testRouter.attach(base + "/users/{userid}/clients/{email}/",ClientsServerResource.class);
		//drouter.attach(base + "/users/{email}",UserServerResource.class);
		//drouter.attach(base + "/users/{id}/clients",UserServerResource.class);
		//drouter.attach(base + "/users/{email}/clients",UserServerResource.class);
		
		// clients
		testRouter.attach(base + "/clients",com.pmonteiro.fasttrial.resource.ClientsServerResource.class);
		testRouter.attach(base + "/clients/",ClientsServerResource.class);
		testRouter.attach(base + "/clients/{id}",ClientServerResource.class);
		testRouter.attach(base + "/clients/{id}/",ClientServerResource.class);
		testRouter.attach(base + "/clients/email/{email}",ClientServerResource.class);
		testRouter.attach(base + "/clients/email/{email}/",ClientServerResource.class);
		//RouteList list = new rout
		//drouter.attach("/clients/{email}",ClientServerResource.class);
		
		testRouter.attachDefault(TestServerResource.class);
		//drouter.attach("/{classname}",UsersServerResource.class);
		//drouter.attach("/{classname}",ClientsServerResource.class);
	}
	
	private Restlet attachFastTrialRouter() {
		
		String base = "/ft";
		
		
		// roles ( load from UserType )
		String ROOT_TYPE 	= "root";
		String TADMIN_TYPE 	= "tadmin";
		String BADMIN_TYPE 	= "badmin";
		String USER_TYPE 	= "user";

		// create users
		UserAccount root = new UserAccount("root","root");
		UserAccount adriano = new UserAccount("adriano","adriano");
		UserAccount rodrigo = new UserAccount("rodrigo","rodrigo");
		
		// groups ...
		UserType rootGroup = new UserType();
		UserType tadminGroup = new UserType();
		rootGroup.getMemberUsers().add(root);
		tadminGroup.getMemberUsers().add(adriano);
		tadminGroup.getMemberUsers().add(rodrigo);

		MemoryRealm mRealm = new MemoryRealm();
		mRealm.getRootGroups().add(rootGroup);
		mRealm.map(rootGroup, this.getRole(ROOT_TYPE));
		
		/*
		mRealm.getUsers().add(root);
		mRealm.map(root,this.getRole(ROOT_TYPE));
		mRealm.map(root,this.getRole(TADMIN_TYPE));
		mRealm.map(root,this.getRole(BADMIN_TYPE));
		mRealm.map(root,this.getRole(USER_TYPE));
		*/

		// Authenticator
		ChallengeAuthenticator authenticator = new ChallengeAuthenticator( getContext(), ChallengeScheme.HTTP_BASIC, "realm");
		authenticator.setVerifier(mRealm.getVerifier());
		authenticator.setEnroler(mRealm.getEnroler());
		
		// Authorizer
		RoleAuthorizer authorizer = new RoleAuthorizer();
		authorizer.getAuthorizedRoles().add(this.getRole(ROOT_TYPE));

		//MethodAuthorizer methodAuth = new MethodAuthorizer();

		ftRouter = new Router(getContext());
		ftRouter.attach(base + "/users",UsersAccountServerResource.class);
		ftRouter.attach(base + "/users/",UsersAccountServerResource.class);
		ftRouter.attach(base + "/users/{id}",UserAccountServerResource.class);
		
		authenticator.setNext(authorizer);
		authorizer.setNext(ftRouter);
		return authenticator;
	}
	
	private void attachTutorialRouter() {
		
		String base = "/tt";

		tutorialRouter = new Router();
		
		tutorialRouter.attach(base + "",TestServerResource.class);
		tutorialRouter.attach(base + "",ConcreteServerResource.class);
	}
	
	private void attachTodoRouter() {
		
		String base = "/todo";

		todoRouter = new Router(this.getContext());
		
        Directory directory = new Directory(getContext(), "clap://class/com/pmonteiro/fasttrial/ui/");
        directory.setDeeplyAccessible(true);

        todoRouter.attach( base + "/web", directory);
		todoRouter.attach( base + "/todos", TodosResource.class);
		todoRouter.attach( base + "/todos/{todoId}", TodoResource.class);
	}
	
	private Router createConfigRouter() {
		return null;
	}
	
	private Router createApiRouter() {
		return null;
	}
}
