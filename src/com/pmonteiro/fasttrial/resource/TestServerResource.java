package com.pmonteiro.fasttrial.resource;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import com.pmonteiro.fasttrial.api.TestResource;

public class TestServerResource extends ServerResource implements TestResource {

	@Override
	public Representation getJson() {
		return new StringRepresentation("get from tmpserverresource");
	}

}
