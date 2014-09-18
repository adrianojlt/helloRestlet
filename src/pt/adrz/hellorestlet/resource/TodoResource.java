package pt.adrz.hellorestlet.resource;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import pt.adrz.hellorestlet.dao.TodoDAOFactory;
import pt.adrz.helloservlet.model.Todo;

public class TodoResource extends ServerResource {

	private TodoDAOFactory content = TodoDAOFactory.getDAOFactory(TodoDAOFactory.TODO_STATIC);
	
	private Long todoId;
	
	protected void doInit() throws ResourceException {
		this.todoId = Long.valueOf(this.getAttribute("todoId"));
	}
	
	@Get
    public Representation get() {

        Todo todo = this.content.get(todoId);

        if (todo == null) { throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND); }

        return new JacksonRepresentation<>(todo);
    }
	
	@Put("json")
    public void update(Representation representation) throws IOException {
        JacksonRepresentation<Todo> jsonRepresentation = new JacksonRepresentation<Todo>(representation, Todo.class);
        Todo todo = jsonRepresentation.getObject();
        this.content.update(todo);
    }
	
	@Delete
	public void remove() {
		this.content.delete(todoId);
	}
}
