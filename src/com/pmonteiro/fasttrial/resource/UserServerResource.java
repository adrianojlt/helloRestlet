package com.pmonteiro.fasttrial.resource;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.pmonteiro.fasttrial.model.User;
import com.pmonteiro.fasttrial.api.UserResource;
import com.pmonteiro.fasttrial.storage.StorageFactory;
import com.pmonteiro.fasttrial.storage.StorageFactory.STORAGE_TYPE;

public class UserServerResource extends ServerResource implements UserResource {
	
	private static String ID = "userid";

	private StorageFactory<User> userDAO = StorageFactory.getUserStorage(STORAGE_TYPE.MYSQL_JDBC);
	
	private User user;
	private Long id = null;
	private String email = "...";
	
	public UserServerResource() { }
	
	//@Override public void init(Context context, Request request, Response response) { }

	protected void doInit() /*throws ResourceException */{

		this.userDAO = StorageFactory.getUserStorage(STORAGE_TYPE.MYSQL_JDBC);
		
		try {
			this.id = Long.valueOf(this.getAttribute(ID));
		}
		catch (NumberFormatException nEx) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		}
	}

	@Override
	public Representation toJson() {
		
		User user = null;

		System.out.println(this.id);
		System.out.println(this.getStatus());

		//user = this.getTmpUser();
		user = userDAO.get(this.id);
		
		if ( user == null ) return new EmptyRepresentation();
		else return new JacksonRepresentation<User>( MediaType.APPLICATION_JSON , user );
	}
	
	private User getTmpUser() {

		User user = new User();

		user.setId(1L);
		user.setName("tmpuser");
		user.setGroupId(3L);

		return user;
	}
}
