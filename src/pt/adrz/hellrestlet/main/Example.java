package pt.adrz.hellrestlet.main;
import org.restlet.Server;
import org.restlet.data.Protocol;

import pt.adrz.hellrestlet.application.HelloWorld;
import pt.adrz.hellrestlet.application.MailServerApplication;
import pt.adrz.hellrestlet.application.SimplePageApplication;


public class Example {
	
	private Server server;
	
	public Example() {
		this.server = new Server(Protocol.HTTP, 8111);
	}
	
	public void mailApp() throws Exception{
		MailServerApplication mailServerApp = new MailServerApplication();
		this.server.setNext(mailServerApp);
		this.server.start();
	}

	public void helloWorld() throws Exception {
		server.setNext(HelloWorld.class);
		server.start();
	}	
	
	public void simplePageApp() throws Exception {
		server.setNext(new SimplePageApplication());
		server.start();
	}
}
