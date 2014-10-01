package com.pmonteiro.fasttrial.resource;

import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.pmonteiro.fasttrial.model.User;
import com.pmonteiro.fasttrial.api.UserResource;
import com.pmonteiro.fasttrial.storage.StorageFactory;
import com.pmonteiro.fasttrial.storage.StorageFactory.STORAGE_TYPE;

public class UserServerResource extends ServerResource implements UserResource {

	private StorageFactory<User> userDAO = StorageFactory.getUserStorage(STORAGE_TYPE.MYSQL_JDBC);
	
	private User user;
	
	private Long id;
	
	private String email;
	
	public UserServerResource() { }
	
	protected void doInit() throws ResourceException {

		

		this.userDAO = StorageFactory.getUserStorage(STORAGE_TYPE.MYSQL_JDBC);

		
	}

	@Override
	public Representation toJson() {
		return null;
	}
}
