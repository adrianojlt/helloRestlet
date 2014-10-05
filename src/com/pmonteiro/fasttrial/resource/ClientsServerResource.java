package com.pmonteiro.fasttrial.resource;

import java.util.List;
import java.util.Map;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.pmonteiro.fasttrial.api.ClientsResource;
import com.pmonteiro.fasttrial.model.Client;
import com.pmonteiro.fasttrial.storage.FactoryClient;
import com.pmonteiro.fasttrial.storage.StorageType;
import com.pmonteiro.server.web.GenericResource;

public class ClientsServerResource extends ServerResource implements ClientsResource {

	private static String ID = "userid";
	
	private enum Option { Empty , All , ByUserId, ByEmail, ByAge }

	private Option option; 
	
	private FactoryClient clientDAO = null;
	private List<Client> clients = null;
	private Client client = null;
	
	private boolean validateEmail(String email) {
		return (email == null ) ? false : true;
	}
	
	private void tmp() {
		Map<String,Object> attr = this.getRequestAttributes();
		String param = getQueryValue("param1");
		System.out.println( param );
	}
	
	protected void doInit() throws ResourceException {

		this.clientDAO = FactoryClient.getClientStorage(StorageType.MYSQL_JDBC);

		String userId = this.getAttribute(ID);
		String email = getQueryValue("email");
		
		if ( this.validateEmail(email) ) {
			this.client = clientDAO.get(email);
			this.option = Option.ByEmail;
		}
		
		if ( userId == null ) { 
			this.clients = clientDAO.list();
			this.option = Option.All;
		}
		else { 
			try { 
				this.clients = clientDAO.list(Long.valueOf(userId)); 
				if ( this.clients == null ) this.option = Option.Empty;
				else this.option = Option.ByUserId;
			}
			catch(NumberFormatException eN) { 
				this.setStatus(Status.CLIENT_ERROR_BAD_REQUEST); 
			}
		}
		
		switch (this.option) {
			case Empty: 
			case All: 
			case ByUserId: 
			case ByEmail: 
			default: 
		}

		//this.tmp();
	}
	
	@Override
	public Representation represent() {
		
		switch (this.option) {
			case Empty: 
				return new EmptyRepresentation();
			case All: 
				return new JacksonRepresentation<>(this.clients);
			case ByUserId: 
				return new JacksonRepresentation<>(this.clients);
			case ByEmail: 
				return new JacksonRepresentation<>(client);
			default: 
				return new EmptyRepresentation();
		}
	}
}
