package com.pmonteiro.fasttrial.api;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface ClientsResource {
	
	@Get
	public Representation represent();

}
