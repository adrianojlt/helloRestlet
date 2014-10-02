package com.pmonteiro.fasttrial.resource;

import java.util.List;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import com.pmonteiro.fasttrial.api.ClientsResource;
import com.pmonteiro.fasttrial.model.Client;
import com.pmonteiro.fasttrial.storage.FactoryClient;
import com.pmonteiro.fasttrial.storage.StorageType;
import com.pmonteiro.server.web.GenericResource;

public class ClientsServerResource extends GenericResource implements ClientsResource {
	
	private FactoryClient clientDAO;
	
	protected void doInit() throws ResourceException {
		this.clientDAO = FactoryClient.getClientStorage(StorageType.MYSQL_JDBC);
	}

	@Override
	public Representation represent() {
		
		List<Client> clients = null;
		String id = this.getAttribute("id");
		
		if ( id == null ) { // all clients ...

			clients = clientDAO.list();
		}
		else { // ... clients from the given user_id
			
			try { 
				clients = clientDAO.list(Long.valueOf(id)); 
			}
			catch(NumberFormatException eN) {
				
				this.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			}
		}

		if ( clients == null ) return new EmptyRepresentation();
		else return new JacksonRepresentation<>(clients);
	}
}
