package com.pmonteiro.server.web;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.LocalReference;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.routing.TemplateRoute;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.RoleAuthorizer;
import org.restlet.security.User;
import org.restlet.util.RouteList;

import pt.adrz.hellorestlet.resource.FirstRestlet;
import pt.adrz.hellorestlet.resource.todo.TodoResource;
import pt.adrz.hellorestlet.resource.todo.TodosResource;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmonteiro.fasttrial.model.accounts.UserAccount;
import com.pmonteiro.fasttrial.model.accounts.UserType;
import com.pmonteiro.fasttrial.resource.ClientServerResource;
import com.pmonteiro.fasttrial.resource.ClientsServerResource;
import com.pmonteiro.fasttrial.resource.ConcreteServerResource;
import com.pmonteiro.fasttrial.resource.TestServerResource;
import com.pmonteiro.fasttrial.resource.UserServerResource;
import com.pmonteiro.fasttrial.resource.UsersServerResource;
import com.pmonteiro.fasttrial.resource.accounts.UserAccountServerResource;
import com.pmonteiro.fasttrial.resource.accounts.UsersAccountServerResource;

public class WebApp extends Application {
	
	// roles ( load from UserType )
	String ROOT_TYPE 	= "root";
	String TADMIN_TYPE 	= "tadmin";
	String BADMIN_TYPE 	= "badmin";
	String USER_TYPE 	= "user";

	private ConfigFactory config;

	private Router ftRouter;
	private Router tutorialRouter;

	private ChallengeAuthenticator authenticator;
	private RoleAuthorizer authorizer;
	
	private UserAccount root;
	private UserAccount adriano;
	private UserAccount rodrigo;
	private UserAccount rui;
	private UserAccount homer;
	
	private List<User> allUsers = new ArrayList<User>();
	
	private UserType rootGroup;
	private UserType tadminGroup;
	private UserType badminGroup;
	private UserType users;
	
	public WebApp() {

		// set Roles for this application ...
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role(this, ROOT_TYPE));
		roles.add(new Role(this, TADMIN_TYPE));
		roles.add(new Role(this, BADMIN_TYPE));
		roles.add(new Role(this, USER_TYPE));
		this.setRoles(roles);
		
		// create users
		root = new UserAccount("root","root");
		adriano = new UserAccount("adriano","adriano");
		rodrigo = new UserAccount("rodrigo","rodrigo");
		rui = new UserAccount("rui","rui");
		homer = new UserAccount("homer","homer");
		
		allUsers.add(root);
		allUsers.add(adriano);
		allUsers.add(rodrigo);
		allUsers.add(rui);
		allUsers.add(homer);
		
		// create groups ...
		rootGroup = new UserType();
		badminGroup = new UserType();
		tadminGroup = new UserType();
		users = new UserType();

		// add users to groups
		rootGroup.getMemberUsers().add(root);
		badminGroup.getMemberUsers().add(rodrigo);
		tadminGroup.getMemberUsers().add(adriano);
		tadminGroup.getMemberUsers().add(rui);
		users.getMemberUsers().add(homer);

	}
	
	private void loadClasses() { }
	
	private void tmp() { }
	
	private void db4oStore() {
		
		ObjectContainer db = null;
		
		//new File("users.data").delete();
		db = Db4o.openFile("users.data");
		
		pt.adrz.hellorestlet.model.User user = new pt.adrz.hellorestlet.model.User();
		user.setName("john");
		//db.set(user);
		db.delete(user);
		db.commit();
		db.close();
	}
	
	private void loadDB4O() {

		ObjectContainer db = Db4o.openFile("users.data");
		
		pt.adrz.hellorestlet.model.User  user = new pt.adrz.hellorestlet.model.User();
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
		apiGuard();
		
		// filters 
		
		// routes
		attachFastTrialRouter();
		attachTutorialRouter();
		

		// chain ...
		tutorialRouter.attachDefault(authenticator);
		authenticator.setNext(this.authorizer);
		authorizer.setNext(ftRouter);
		//ftRouter.attachDefault(testRouter);
		
		return tutorialRouter;
	}


	@Override
	public Restlet createOutboundRoot() {
		return null;
	}
	
	private void loadConfig() {
		this.config = ConfigFactory.getConfigFactory(ConfigFactory.STORAGE_TYPE.STATIC);
	}
	
	private void apiGuard() {
		
		MemoryRealm mRealm = new MemoryRealm();

		mRealm.setUsers(allUsers);
		mRealm.getRootGroups().add(tadminGroup);

		/*
		mRealm.map(rootGroup, this.getRole(ROOT_TYPE));
		mRealm.map(rootGroup, this.getRole(TADMIN_TYPE));
		mRealm.map(rootGroup, this.getRole(BADMIN_TYPE));
		mRealm.map(rootGroup, this.getRole(USER_TYPE));
		mRealm.map(tadminGroup, this.getRole(ROOT_TYPE));
		mRealm.map(tadminGroup, this.getRole(USER_TYPE));
		*/
		
		mRealm.map(tadminGroup,this.getRole(ROOT_TYPE));
		/*
		mRealm.getUsers().add(root);
		mRealm.map(root,this.getRole(ROOT_TYPE));
		mRealm.map(root,this.getRole(TADMIN_TYPE));
		mRealm.map(root,this.getRole(BADMIN_TYPE));
		mRealm.map(root,this.getRole(USER_TYPE));
		*/

		// Authenticator
		this.authenticator = new ChallengeAuthenticator( getContext(), ChallengeScheme.HTTP_BASIC, "WebApp");
		authenticator.setVerifier(mRealm.getVerifier());
		authenticator.setEnroler(mRealm.getEnroler());
		
		// Authorizer
		this.authorizer = new RoleAuthorizer();
		authorizer.getAuthorizedRoles().add(this.getRole(ROOT_TYPE));
		//MethodAuthorizer methodAuth = new MethodAuthorizer();
	}
	
	private void attachFastTrialRouter() {
		
		String base = "/ft";
		
		ftRouter = new Router(getContext());
		
		//RouteList rt = ftRouter.getRoutes();
		//List<org.restlet.routing.Route> lista = new ArrayList<org.restlet.routing.Route>();
		//RouteList rt2 = new RouteList(lista);

				
		//ftRouter.setRoutes(rt2);
		ResourceRouteList rList = new ResourceRouteList();

		ResourceRoute tr = new ResourceRoute(ftRouter, "/users", UsersAccountServerResource.class,this);
		//TemplateRoute tr = new TemplateRoute(ftRouter, "/users", this.createFinder(UsersAccountServerResource.class));
		
		rList.add(tr);
		//TemplateRoute as = new TemplateRoute(ftRouter, "/users/{id}", this.createFinder(UsersAccountServerResource.class));

		//rt.add(tr);
		//rt.ad

		RouteList myroutelist = new RouteList();
		myroutelist.addAll(rList);
		ftRouter.setRoutes(myroutelist);

		//ftRouter.attach(base + "/users",UsersAccountServerResource.class);
		//ftRouter.attach(base + "/users/",UsersAccountServerResource.class);
		//ftRouter.attach(base + "/users/{id}",UserAccountServerResource.class);
		
		authenticator.setNext(authorizer);
		authorizer.setNext(ftRouter);
		//return authenticator;
	}
	
	private void attachTutorialRouter() {
		
		String base = "/tt";

		tutorialRouter = new Router();
		
		tutorialRouter.attach(base + "",TestServerResource.class);
		tutorialRouter.attach(base + "",ConcreteServerResource.class);
	}
	
	private Router createConfigRouter() {
		return null;
	}
	
	private Router createApiRouter() {
		return null;
	}
	
	//public hadler(Request request,Response response)
}
