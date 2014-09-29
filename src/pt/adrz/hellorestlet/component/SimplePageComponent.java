package pt.adrz.hellorestlet.component;

import java.util.ArrayList;
import java.util.List;

import org.restlet.Application;
import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
import org.restlet.security.MemoryRealm;
import org.restlet.security.User;

import pt.adrz.hellorestlet.application.MailServerApplication;
import pt.adrz.hellorestlet.application.SimplePageApplication;

public class SimplePageComponent extends Component{
	
	private Application application;
	
	private List<User> users;
	
	private User admin,manager,user1,user2;

	
	public SimplePageComponent() {
		
		this.setName("Simple component to manage TODO's");
		this.setDescription("Component to add remove and update TODO'S");
		this.setOwner("me");
		
		this.connectors();
		this.hosts();
		//this.createUsers();
		//this.security();
		this.logs();

	}
	
	private void connectors() {

		this.getClients().add(new Client(Protocol.CLAP));
		
		Server server = new Server(new Context(), Protocol.HTTP, 8111);
		this.getServers().add(server);
	}
	
	private void hosts() {

		VirtualHost host = this.getDefaultHost();
		this.application = new SimplePageApplication();
		host.attach("/v1", this.application);
		host.attach("/mail", new MailServerApplication());

		//host.attach("/v2", this.application);
		host.attachDefault(this.application);
		//this.setDefaultHost(host);
	}
	
	
	private void createUsers() {

		this.users = new ArrayList<User>();
		
		this.admin = new User("admin", "admin");
		this.manager = new User("manager", "manager");
		this.user1 = new User("user1", "00");
		this.user2 = new User("user2", "00");
	}
	
	private void security() {

		MemoryRealm realm = new MemoryRealm();

		realm.getUsers().add(this.admin);
		realm.getUsers().add(this.manager);
		realm.getUsers().add(this.user1);
		realm.getUsers().add(this.user2);
		
		realm.map(admin, application.getRole("admin"));
		realm.map(manager, application.getRole("manager"));
		realm.map(manager, application.getRole("user"));
		realm.map(user1, application.getRole("user"));
		realm.map(user2, application.getRole("user"));
		
		this.application.getContext().setDefaultEnroler(realm.getEnroler());
		this.application.getContext().setDefaultVerifier(realm.getVerifier());
	}
	
	private void logs() {
		
	}
	
}
