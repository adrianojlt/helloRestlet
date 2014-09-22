package pt.adrz.hellorestlet.resource;

import java.io.IOException;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.xstream.XstreamRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import pt.adrz.hellorestlet.dao.TodoDAOFactory;
import pt.adrz.hellorestlet.model.Todo;

public class TodoResources extends ServerResource {

	private TodoDAOFactory content = TodoDAOFactory.getDAOFactory(TodoDAOFactory.TODO_STATIC);

	@Get("json")
	public Representation list() {
		return new JacksonRepresentation<>(content.list());
	}
	
	@Get("xml")
	public Representation listXml() {
		return new XstreamRepresentation<>(content.list());
	}

	@Post("json")
	public void create(Representation representation) throws IOException {
	    JacksonRepresentation<Todo> jsonRepresentation = new JacksonRepresentation<Todo>(representation, Todo.class);
	    Todo todo = jsonRepresentation.getObject();
	    content.create(todo);
	}
}
