package pt.adrz.hellorestlet.component;

import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;

import pt.adrz.hellorestlet.application.SimplePageApplication;

public class SimplePageComponent extends Component{
	
	public SimplePageComponent() {
		
		this.setName("Simple component to manage TODO's");
		this.setDescription("Component to add remove and update TODO'S");
		this.setOwner("me");
		
		this.connectors();
		this.hosts();
		this.logs();
	}
	
	private void connectors() {

		this.getClients().add(new Client(Protocol.CLAP));
		
		Server server = new Server(new Context(), Protocol.HTTP, 8111);
		this.getServers().add(server);
	}
	
	private void hosts() {
		VirtualHost host = this.getDefaultHost();
		host.attachDefault(new SimplePageApplication());
	}
	
	private void logs() {
		
	}
}
