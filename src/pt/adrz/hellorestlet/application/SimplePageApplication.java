package pt.adrz.hellorestlet.application;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.engine.util.SystemUtils;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.ext.wadl.WadlApplication;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.User;

import pt.adrz.hellorestlet.filter.CorsFilter;
import pt.adrz.hellorestlet.resource.FirstRestlet;
import pt.adrz.hellorestlet.resource.HelloWorld;
import pt.adrz.hellorestlet.resource.HowToRestlet;
import pt.adrz.hellorestlet.resource.MainPageRestlet;
import pt.adrz.hellorestlet.resource.RootResource;
import pt.adrz.hellorestlet.resource.TmpRestlet;
import pt.adrz.hellorestlet.resource.TodoResource;
import pt.adrz.hellorestlet.resource.TodoResources;

public class SimplePageApplication extends Application {
	
	public SimplePageApplication() {
		super();
		this.setName("simple page application");
		setDescription("example todo");
	    setOwner("Restlet S.A.S.");
	    setAuthor("The Restlet Team");
		
		// declare roles
		this.getRoles().add(new Role(this,"admin"));
		this.getRoles().add(new Role(this,"manager"));
		this.getRoles().add(new Role(this,"user"));
	}

	public SimplePageApplication(Context parentContext) {
		super(parentContext);
	}
	
	//public SimplePageApplication(Context parentContext) { super(parentContext); }
	
	@Override
	public synchronized Restlet createInboundRoot() {

		System.out.println("createinboundroot");

		Router testRouter = this.getTestRouter();
		Router todoRouter = this.getTodoRouter();
		
		// apply a filter after receiving a call ...
		CorsFilter cFilter = new CorsFilter(this.getContext());
		
		ChallengeAuthenticator authenticator = this.getAuth();
		//authenticator.setNext(router);

		cFilter.setNext(testRouter);
		testRouter.attachDefault(todoRouter);
		return cFilter;
		//return testRouter;
	}
	
	@Override
	public Restlet createOutboundRoot(){
		//System.out.println("createoutboundroot");
		return new CorsFilter(this.getContext());
		//return null;
	}
	
	private ChallengeAuthenticator getAuth() {
		
		User admin = new User("admin", "admin");
		User manager = new User("manager", "manager");
		User user1 = new User("user1", "00");
		User user2 = new User("user2", "00");

		ChallengeAuthenticator authenticator = new ChallengeAuthenticator(this.getContext(), ChallengeScheme.HTTP_BASIC, "My Realm");
		
		// set authorization ...
		//RoleAuthorizer authorizer = new RoleAuthorizer();
		//authorizer.getAuthorizedRoles().add(this.getRole("manager"));
		//authenticator.setNext(authorizer);
		
		MemoryRealm realm = new MemoryRealm();

		realm.getUsers().add(admin);
		realm.getUsers().add(manager);
		realm.getUsers().add(user1);
		realm.getUsers().add(user2);
		
		realm.map(admin, this.getRole("admin"));
		realm.map(manager, this.getRole("manager"));
		realm.map(manager, this.getRole("user"));
		realm.map(user1, this.getRole("user"));
		realm.map(user2, this.getRole("user"));
		
		this.getContext().setDefaultEnroler(realm.getEnroler());
		this.getContext().setDefaultVerifier(realm.getVerifier());
		
		authenticator.setVerifier(realm.getVerifier());
		authenticator.setEnroler(realm.getEnroler());
		
		return authenticator;
	}
	
	private Router getTodoRouter() {

		Router router = new Router(this.getContext());

		// Todo app ...
		router.attach("/rest/howto", new HowToRestlet());
		router.attach("/rest/todos", TodoResources.class);
		router.attach("/rest/todos/{todoId}", TodoResource.class);
		
		return router;
	}
	
	private Router getTestRouter() {
		
		Restlet tmpRestlet = new TmpRestlet(this);
		
		// ... to serve static files (class path)
		Directory directory = new Directory(this.getContext(), "clap://index.html");
		directory.setDeeplyAccessible(true);
		
		Router router = new Router(this.getContext());

		router.attach("/", new MainPageRestlet());
		//router.attach("/web", directory);
		router.attach("/root", RootResource.class);
		router.attach("/firstrestlet", new FirstRestlet());
		router.attach("/mainpage", new MainPageRestlet());
		router.attach("/helloworld", HelloWorld.class);
		router.attach("/tmp",tmpRestlet);
		
		return router;
	}
	
	/**
	 * Basic Authentication
	 * @return
	 */
	private ChallengeAuthenticator getAuthChall() {
		ChallengeAuthenticator auth = new ChallengeAuthenticator(this.getContext(), ChallengeScheme.HTTP_BASIC, "my realm");
		//MySecretVerifier myVerifier = new MySecretVerifier();
		//auth.setVerifier(myVerifier);
		return auth;
	}
	
	/**
	 * Digest Auth
	 * @return
	 */
	private DigestAuthenticator getAuthDigest() {
		DigestAuthenticator auth = new DigestAuthenticator(getContext(), "my realm", "my server key");
		MapVerifier verifier = new MapVerifier();
		verifier.getLocalSecrets().put("00", "00".toCharArray());
		auth.setWrappedVerifier(verifier);
		return auth;
	}
}
