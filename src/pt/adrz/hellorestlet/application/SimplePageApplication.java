package pt.adrz.hellorestlet.application;


//import org.apache.catalina.connector.Request;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;
import org.restlet.security.Role;
import org.restlet.security.RoleAuthorizer;

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
		
		// declare roles
		this.getRoles().add(new Role(this,"manager"));
		this.getRoles().add(new Role(this,"user"));
		
		// test 
	}

	public SimplePageApplication(Context parentContext) {
		super(parentContext);
	}
	
	//public SimplePageApplication(Context parentContext) { super(parentContext); }
	
	@Override
	public synchronized Restlet createInboundRoot() {

		Restlet tmpRestlet = new TmpRestlet(this);
		
		// ... to serve static files
		Directory directory = new Directory(this.getContext(), "clap://index.html");
		directory.setDeeplyAccessible(true);
		
		Router router = new Router(getContext());

		router.attach("/", new MainPageRestlet());
		router.attach("/web", directory);
		router.attach("/root", RootResource.class);
		router.attach("/firstrestlet", new FirstRestlet());
		router.attach("/mainpage", new MainPageRestlet());
		router.attach("/helloworld", HelloWorld.class);
		router.attach("/tmp",tmpRestlet);

		//router.attach("http://localhost:8111/firstresource", FirstResource.class);
		
		// Todo app ...
		router.attach("/howto", new HowToRestlet());
		router.attach("/rest/todos", TodoResources.class);
		router.attach("/rest/todos/{todoId}", TodoResource.class);
		
		// manage users
		
		// apply a filter after receiving a call ...
		CorsFilter cFilter = new CorsFilter(this.getContext());
		
		// simple http authentication ...
		//ChallengeAuthenticator authenticator = this.getAuthChall();
		//Authenticator auth = this.getAuthDigest();

		// set authorization ...
		RoleAuthorizer authorizer = new RoleAuthorizer();
		//authorizer.getAuthorizedRoles().add(this.getRole("manager"));

		authorizer.setNext(router);

		ChallengeAuthenticator authenticator = new ChallengeAuthenticator(this.getContext(), ChallengeScheme.HTTP_BASIC, "My Realm");

		authenticator.setNext(authorizer);

		//cFilter.setNext(router);
		
		return authenticator;
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
