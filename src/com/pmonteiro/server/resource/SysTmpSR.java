package com.pmonteiro.server.resource;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import com.jalapeno.tools.SchemaBuilder;

public class SysTmpSR extends ServerResource{
	
	public Representation echo() {
		SchemaBuilder sb = new SchemaBuilder(null);
		return new StringRepresentation("<html>hello from sys</html>", MediaType.TEXT_HTML);
	}	
	
	@Get("json|html")
	public Representation sysProperties() {
		//ClassLoader loader = 
		 //String property = "user.dir = " + System.getProperty("java.class.path");
		 String property = "user.dir = " + System.getProperty("user.dir");
		return new StringRepresentation(property);
	}
}
