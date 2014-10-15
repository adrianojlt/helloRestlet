package com.pmonteiro.fasttrial.resource;

import java.util.List;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;

import pt.adrz.hellorestlet.model.Client;

import com.pmonteiro.fasttrial.storage.FactoryClient;
import com.pmonteiro.fasttrial.storage.StorageType;
import com.pmonteiro.server.web.GenericResource;

public class ConcreteServerResource extends GenericResource {
	
	private FactoryClient clientDAO = FactoryClient.getClientStorage(StorageType.MYSQL_JDBC);

	@Get
	public Representation tmpGet() {
		
		List<Client> clients = clientDAO.list();

		String name = getAttribute("classname");

		return new StringRepresentation(name);
	}
}
