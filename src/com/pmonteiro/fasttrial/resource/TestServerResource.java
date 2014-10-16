package com.pmonteiro.fasttrial.resource;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import pt.adrz.hellorestlet.dao.usermanager.FactoryUser;

import com.pmonteiro.fasttrial.api.TestResource;
import com.pmonteiro.fasttrial.storage.StorageType;

public class TestServerResource extends ServerResource implements TestResource {
	
	private FactoryUser userDAO;
	
	public TestServerResource() { }
	
	protected void doInit() throws ResourceException {
		this.userDAO = FactoryUser.getUserStorage(StorageType.DB4O);
	}

	@Override
	public Representation getJson() {
		return new StringRepresentation("get from tmpserverresource");
	}

	@Override
	public Representation getHtml() {
		userDAO.tmp();
		String html = "<html>getHtml</html>";
		return new StringRepresentation(html,MediaType.TEXT_HTML);
	}

	@Override
	public Representation postData(Representation data) {
		return null;
	}
}
