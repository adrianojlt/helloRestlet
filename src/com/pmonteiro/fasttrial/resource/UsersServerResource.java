package com.pmonteiro.fasttrial.resource;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.pmonteiro.fasttrial.model.User;
import com.pmonteiro.fasttrial.api.UsersResource;
import com.pmonteiro.fasttrial.storage.StorageFactory;
import com.pmonteiro.fasttrial.storage.StorageFactory.STORAGE_TYPE;

public class UsersServerResource extends ServerResource implements UsersResource {
	
	private StorageFactory<User> userDAO = StorageFactory.getUserStorage(STORAGE_TYPE.MYSQL_JDBC);
	
	public UsersServerResource() { }

	@Override
	public Representation get() throws ResourceException {
		return new StringRepresentation("get");
	}
}
