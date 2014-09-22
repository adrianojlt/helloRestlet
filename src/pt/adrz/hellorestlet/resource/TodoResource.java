package pt.adrz.hellorestlet.resource;

import java.io.IOException;

import javax.xml.transform.dom.DOMResult;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.ext.xml.TransformRepresentation;
import org.restlet.ext.xstream.XstreamRepresentation;
//import org.restlet.ext.x
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.events.DocumentEndEvent;

import pt.adrz.hellorestlet.dao.TodoDAOFactory;
import pt.adrz.hellorestlet.model.Todo;

public class TodoResource extends ServerResource {

	private TodoDAOFactory content = TodoDAOFactory.getDAOFactory(TodoDAOFactory.TODO_STATIC);
	
	private Long todoId;
	
	protected void doInit() throws ResourceException {
		this.todoId = Long.valueOf(this.getAttribute("todoId"));
		this.getVariants().add(new Variant(MediaType.APPLICATION_XML));
		this.getVariants().add(new Variant(MediaType.APPLICATION_JSON));
	}
	
	@Override
    public Representation get(Variant variant) {
		
		Representation result = null;

        Todo todo = this.content.get(todoId);

        if (todo == null) { throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND); }
        
        if ( MediaType.APPLICATION_JSON.isCompatible(variant.getMediaType())) {
        	result = new JacksonRepresentation<Todo>(todo);
        }
        if ( MediaType.APPLICATION_XML.isCompatible(variant.getMediaType())) {
        	result = new XstreamRepresentation<Todo>(todo);
        }
        
        result = new JacksonRepresentation<Todo>(todo);

        return result;
    }
	
	public Representation getFreemarker() {
        Todo todo = this.content.get(todoId);
        return null;
	}
	
	//@Get
	public Representation getXml() throws Exception{

        Todo todo = this.content.get(todoId);
        
        DomRepresentation result = new DomRepresentation();

        result.setIndenting(true);
        result.setNamespaceAware(true);
        
        Document document = result.getDocument();

        Node todoElt = document.createElementNS( "http://www.rmep.org/namespaces/1.0", "todo");
        Node id = document.createElement("id");
        Node title = document.createElement("name");


        id.setTextContent(todo.getId().toString());
        title.setTextContent(todo.getTitle());
        
        document.appendChild(todoElt);
        todoElt.appendChild(id);
        todoElt.appendChild(title);
        
        //TransformRepresentation t = new trans
        
        return result;
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
