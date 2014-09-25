package pt.adrz.hellorestlet.resource;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.wadl.ApplicationInfo;
import org.restlet.ext.wadl.DocumentationInfo;
import org.restlet.ext.wadl.MethodInfo;
import org.restlet.ext.wadl.RepresentationInfo;
import org.restlet.ext.wadl.WadlServerResource;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.ext.xstream.XstreamRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import pt.adrz.hellorestlet.dao.DataType;
import pt.adrz.hellorestlet.dao.TodoDAOFactory;
import pt.adrz.hellorestlet.dao.TodoDAOFactory.*;
import pt.adrz.hellorestlet.model.Todo;

public class TodoResource extends WadlServerResource {

	private TodoDAOFactory content = TodoDAOFactory.getDAOFactory(DataType.STATIC);
	
	private Long todoId;
	
	/*
	@Override
	protected void describe(ApplicationInfo applicationInfo) {

		super.describe(applicationInfo);

		RepresentationInfo rep = new RepresentationInfo(MediaType.TEXT_PLAIN);
        rep.setIdentifier("Todo");
        applicationInfo.getRepresentations().add(rep);

        DocumentationInfo doc = new DocumentationInfo();
        doc.setTitle("Todo");
        doc.setTextContent("Simple TODO");
        rep.getDocumentations().add(doc);
	}
	*/
	
	/*
	@Override
	protected RepresentationInfo describe( MethodInfo methodInfo, Class<?> representationClass, Variant variant) {
	    RepresentationInfo result = super.describe(methodInfo, representationClass, variant);
	    result.setReference("todo");
	    return result;
	    */
	    //result.setMediaType(MediaType.TEXT_PLAIN);
	    //result.setIdentifier("root");
	    //DocumentationInfo doc = new DocumentationInfo();
	    //doc.setTitle("Mail application");
	    //doc.setTextContent("Simple string welcoming the user to the mail application");
	    //result.getDocumentations().add(doc);
	    //return result;
	//}
	
	protected void doInit() throws ResourceException {


		this.setAutoDescribing(false);
		this.setName("todo");
		this.setDescription("todo description");
		
		String id = getAttribute("todoId");
		
		if ( id == null) return;
		
		try { this.todoId = Long.valueOf(id); }
		catch(Exception e) { e.printStackTrace(); }

		this.getVariants().add(new Variant(MediaType.APPLICATION_XML));
		this.getVariants().add(new Variant(MediaType.APPLICATION_JSON));
		this.getVariants().add(new Variant(MediaType.TEXT_HTML));
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
        if ( MediaType.TEXT_HTML.isCompatible(variant.getMediaType()) ) {
        	StringBuilder html = new StringBuilder();
        	html.append("<html>");
        	html.append("<body>");
        	html.append("<ol>");
        	html.append("<li>id: "+todo.getId()+"</li>");
        	html.append("<li>title: "+todo.getTitle()+"</li>");
        	html.append("</ol>");
        	html.append("</body>");
        	html.append("</html>");
        	StringRepresentation representation = new StringRepresentation(html);
        	representation.setMediaType(MediaType.TEXT_HTML);
        	return representation;
        }
        
        return result;
        //if ( this.isInRole("manager") ) return result;
        //else throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN);
    }
	
	public Representation getFreemarker() {
        Todo todo = this.content.get(todoId);
        return null;
	}
	
	@Post("html")
	public String getDocument(Variant variant) {
		StringBuilder html = new StringBuilder();
	    html.append("<html>\n");
	    html.append("  <body>\n");
	    html.append("    <h2>Hello RESTful World</h2>\n");
	    html.append("<br> MediaType: " + variant.getMediaType().toString());
	    html.append("   </body>\n");
	    html.append("</html>\n");
	    return html.toString();
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
    public Representation update(Representation representation) throws IOException {
        JacksonRepresentation<Todo> jsonRepresentation = new JacksonRepresentation<Todo>(representation, Todo.class);
        Todo todo = jsonRepresentation.getObject();
        this.content.update(todo);
        return new EmptyRepresentation();
    }
	
	public Representation delete(Variant variant) {
		this.content.delete(todoId);
		return new EmptyRepresentation();
	}
}
