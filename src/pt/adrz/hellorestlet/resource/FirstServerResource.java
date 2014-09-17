package pt.adrz.hellorestlet.resource;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class FirstServerResource extends ServerResource{
	
	@Get
	public Representation sayHello() {
		String txt = "First ServerResource with Restlet";
		Representation representation = new StringRepresentation(txt,MediaType.TEXT_PLAIN);
		return representation;
	}
}
