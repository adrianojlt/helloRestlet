package com.pmonteiro.fasttrial.api;

import java.io.IOException;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import com.pmonteiro.fasttrial.model.test.User;

public interface UsersResource {
	
	@Get("html") 
	public Representation toHTML(); 

	@Get("json") 
	public Representation toJson();
	
	@Post("json")
	public Representation create(Representation r) throws ResourceException;
	
}
