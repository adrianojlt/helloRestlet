package com.pmonteiro.fasttrial.resource.accounts;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class UsersAccountServerResource extends ServerResource {
	
	@Get("json")
	public Representation getUsers() {
		return null;
	}

}
