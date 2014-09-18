package pt.adrz.hellorestlet.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HelloWorld extends ServerResource {
	
	@Get
	public String present() {
		return "hello, world";
	}
}
