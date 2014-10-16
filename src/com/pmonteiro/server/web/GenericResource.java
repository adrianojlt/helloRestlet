package com.pmonteiro.server.web;

import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import pt.adrz.hellorestlet.dao.usermanager.StorageFactory;

import com.pmonteiro.server.commons.Resource;

public abstract class GenericResource extends ServerResource  implements Resource {
	
	public Representation toJson() {

		return null;
	}
}
