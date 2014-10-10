package com.pmonteiro.fasttrial.model;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface ClientResource {

	@Get
	public Representation represent();

}
