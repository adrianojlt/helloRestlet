package com.pmonteiro.fasttrial.api;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface UserResource {

	@Get("json") 
	public Representation toJson();

}
