package com.pmonteiro.fasttrial.resource.accounts;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class UserAccountServerResource extends ServerResource {

	@Get("json|html")
	public Representation getUsers() {
		return new StringRepresentation("<html>get User</html>", MediaType.TEXT_HTML);
	}
}
