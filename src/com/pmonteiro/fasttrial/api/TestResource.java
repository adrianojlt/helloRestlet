package com.pmonteiro.fasttrial.api;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface TestResource {
	
	@Get("json")
	public Representation getJson();
}
