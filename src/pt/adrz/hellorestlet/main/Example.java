package pt.adrz.hellorestlet.main;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

import pt.adrz.hellorestlet.application.HelloWorld;
import pt.adrz.hellorestlet.application.MailServerApplication;
import pt.adrz.hellorestlet.application.SimplePageApplication;
import pt.adrz.hellorestlet.application.TmpApplication;
import pt.adrz.hellorestlet.component.TmpComponent;


public class Example {
	
	private Server server;
	
	public Example() {
		this.server = new Server(Protocol.HTTP, 8111);
	}
	
	public void tmpComponent() throws Exception {
		Component cmp = new TmpComponent();
		cmp.start();
	}
	
	public void tmpApp() throws Exception {
		Application tmpApp = new TmpApplication();
		this.server.setNext(tmpApp);
		this.server.start();
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
