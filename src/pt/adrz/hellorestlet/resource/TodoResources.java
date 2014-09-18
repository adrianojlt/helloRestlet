package pt.adrz.hellorestlet.resource;

import java.io.IOException;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import pt.adrz.hellorestlet.dao.TodoDAOFactory;
import pt.adrz.helloservlet.model.Todo;

public class TodoResources extends ServerResource {

	private TodoDAOFactory content = TodoDAOFactory.getDAOFactory(TodoDAOFactory.TODO_STATIC);

	@Get
	public Representation list() {
		return new JacksonRepresentation<>(content.list());
	}

	@Post("json")
	public void create(Representation representation) throws IOException {
	    JacksonRepresentation<Todo> jsonRepresentation = new JacksonRepresentation<Todo>(representation, Todo.class);
	    Todo todo = jsonRepresentation.getObject();
	    content.create(todo);
	}
}
