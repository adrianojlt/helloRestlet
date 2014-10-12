package com.pmonteiro.fasttrial.api;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface TestResource {
	
	@Get("json")
	public Representation getJson();
	
	@Get("html")
	public Representation getHtml();
	
	@Post("json")
	public Representation postData(Representation data);
}
