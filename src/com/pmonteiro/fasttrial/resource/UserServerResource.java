package com.pmonteiro.fasttrial.resource;

import org.restlet.resource.ServerResource;

import com.pmonteiro.fasttrial.model.User;
import com.pmonteiro.fasttrial.api.UserResource;
import com.pmonteiro.fasttrial.storage.StorageFactory;
import com.pmonteiro.fasttrial.storage.StorageFactory.STORAGE_TYPE;

public class UserServerResource extends ServerResource implements UserResource {

	private StorageFactory<User> userDAO = StorageFactory.getUserStorage(STORAGE_TYPE.MYSQL_JDBC);
	
	public UserServerResource() { }
	
	
}
