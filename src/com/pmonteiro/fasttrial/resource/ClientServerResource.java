package com.pmonteiro.fasttrial.resource;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import com.pmonteiro.fasttrial.api.ClientResource;
import com.pmonteiro.fasttrial.model.Client;
import com.pmonteiro.fasttrial.storage.FactoryClient;
import com.pmonteiro.fasttrial.storage.StorageType;
import com.pmonteiro.server.web.GenericResource;

public class ClientServerResource extends GenericResource implements ClientResource {

	private static String ID = "id";
	private static String EMAIL = "email";

	private FactoryClient clientDAO = null;
	private Client client = null;
	
	@Override
	protected void doInit() throws ResourceException {

		this.clientDAO = FactoryClient.getClientStorage(StorageType.MYSQL_JDBC);

		String id = this.getAttribute(ID);
		String email = this.getAttribute(EMAIL);
		
		if ( id != null) {
			try {
				client = clientDAO.get(Long.parseLong(id));
			}
			catch(NumberFormatException eN) { 
				this.setStatus(Status.CLIENT_ERROR_BAD_REQUEST); 
			}
		}

		if ( email != null) { client = clientDAO.get(email); }
	}

	@Override
	public Representation represent() {
		
		if ( this.client != null ) return new JacksonRepresentation<Client>(this.client);
		else return new EmptyRepresentation();
	}
}
