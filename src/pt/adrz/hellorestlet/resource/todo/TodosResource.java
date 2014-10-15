package pt.adrz.hellorestlet.resource.todo;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.wadl.MethodInfo;
import org.restlet.ext.wadl.RepresentationInfo;
import org.restlet.ext.wadl.WadlServerResource;
import org.restlet.ext.xstream.XstreamRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import pt.adrz.hellorestlet.dao.DataType;
import pt.adrz.hellorestlet.dao.TodoDAOFactory;
import pt.adrz.hellorestlet.model.Todo;

public class TodosResource extends WadlServerResource {

	private TodoDAOFactory content = TodoDAOFactory.getDAOFactory(DataType.STATIC);
	
	  @Override
	    protected Representation describe() {
	        //setTitle("List of items.");
	        return super.describe();
	    }
	  
	  @Override
	    protected void describeGet(MethodInfo info) {
	        info.setIdentifier("items");
	        info.setDocumentation("Retrieve the list of current items.");

	        RepresentationInfo repInfo = new RepresentationInfo(MediaType.TEXT_XML);
	        repInfo.setXmlElement("items");
	        repInfo.setDocumentation("List of items as XML file");
	        info.getResponse().getRepresentations().add(repInfo);
	    }

	@Get("json|html")
	public Representation list() {
		return new JacksonRepresentation<>(content.list());
	}
	
	@Get("xml")
	public Representation listXml() {
		return new XstreamRepresentation<>(content.list());
	}

	@Post("json")
	public void create(Representation representation) throws IOException {
		if ( representation == null ) throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
	    JacksonRepresentation<Todo> jsonRepresentation = new JacksonRepresentation<Todo>(representation, Todo.class);
	    Todo todo = jsonRepresentation.getObject();
	    content.create(todo);
	}
}
