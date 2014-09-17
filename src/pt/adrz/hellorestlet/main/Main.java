package pt.adrz.hellorestlet.main;

import org.restlet.resource.ClientResource;

public class Main {
	
	public Main() { }

	public static void main(String[] args) throws Exception { 
		
		Example example = new Example();
		
		//example.mailApp();
		//example.helloWorld();
		//example.simplePageApp();
		//example.tmpComponent();
		example.tmpApp();
		//example.serverResource();
		//standAloneClient();
	}
	
	public static void standAloneClient() throws Exception {
		ClientResource client = new ClientResource("http://www.google.com/");
		client.get().write(System.out);
	}
	
	public static void tmp() {

	}
}
