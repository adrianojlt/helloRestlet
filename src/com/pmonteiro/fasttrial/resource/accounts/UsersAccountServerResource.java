package com.pmonteiro.fasttrial.resource.accounts;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.pmonteiro.fasttrial.model.accounts.UserAccount;
import com.pmonteiro.fasttrial.storage.FactoryUser;
import com.pmonteiro.fasttrial.storage.StorageType;

public class UsersAccountServerResource extends ServerResource {
	
	private FactoryUser userData = FactoryUser.getUserStorage(StorageType.CACHE);
	
	public Representation getUsers() {
		return new StringRepresentation("<html> get users</html>", MediaType.TEXT_HTML);
	}
	
	@Get("json|html")
	public Representation getOneUser() {
		
		UserAccount user = userData.getTmpUser();
		if ( user == null ) return new EmptyRepresentation();
		JacksonRepresentation<UserAccount> jUser = new JacksonRepresentation<UserAccount>(user);
		return jUser;
	}
}
