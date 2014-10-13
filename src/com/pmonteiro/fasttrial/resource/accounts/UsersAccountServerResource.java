package com.pmonteiro.fasttrial.resource.accounts;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class UsersAccountServerResource extends ServerResource {
	
	@Get("json")
	public Representation getUsers() {
		return new StringRepresentation("<html> get users</html>", MediaType.TEXT_HTML);
	}
}
