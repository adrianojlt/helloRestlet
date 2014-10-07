package com.pmonteiro.server.commons;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface Resource {
	
	@Get("json")
	public Representation toJson();
}
