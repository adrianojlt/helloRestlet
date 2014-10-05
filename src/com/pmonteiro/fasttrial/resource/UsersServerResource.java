package com.pmonteiro.fasttrial.resource;

import java.sql.SQLException;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.fasterxml.jackson.core.JsonParseException;
import com.pmonteiro.fasttrial.model.User;
import com.pmonteiro.fasttrial.api.UsersResource;
import com.pmonteiro.fasttrial.storage.FactoryUser;
import com.pmonteiro.fasttrial.storage.StorageType;

public class UsersServerResource extends ServerResource implements UsersResource {
	
	private FactoryUser userDAO;
	
	public UsersServerResource() { }
	
	protected void doInit() throws ResourceException {
		this.userDAO = FactoryUser.getUserStorage(StorageType.MYSQL_JDBC);
	}
	
	@Override
	public Representation toJson() throws ResourceException {
		List<User> users = userDAO.list();
		return new JacksonRepresentation<>(users);
	}

	@Override
	public Representation toHTML() {
		return new StringRepresentation("number of users: " + userDAO.list().size());
	}

	@Override
	public Representation create(Representation r) throws ResourceException {
		
		if ( r == null ) throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST," you must send data ..");

		User user = null;
		boolean stored = false;

		try {
			//new ObjectMapper().readValue(r,User.class);
			//new JacksonRepresentation<User>(MediaType.APPLICATION_JSON, user);
			JacksonRepresentation<User> jsonRepresentation = new JacksonRepresentation<User>(r, User.class);
			user = jsonRepresentation.getObject();
		}
		catch(JsonParseException j) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Error parsing data");
		}
		catch(Exception e) {
			//e.printStackTrace();
			throw new ResourceException(e);
		}

		try {
			stored = this.userDAO.create(user);
		} catch (SQLException e) { 
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"Error persisting data");
		} catch (Exception e) { 
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL,"unknown error"); 
		}
		
		//throw new ResourceException(Status.);

		//return new EmptyRepresentation();
		if ( stored ) return new JacksonRepresentation<>(user);
		else return new StringRepresentation("{msg:'user not created'}",MediaType.APPLICATION_JSON);
	}
}
