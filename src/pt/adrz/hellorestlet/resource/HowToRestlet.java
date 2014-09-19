package pt.adrz.hellorestlet.resource;

import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.Response;
import org.restlet.Request;
import org.restlet.Restlet;

public class HowToRestlet extends Restlet {

	@Override
	public void handle(Request request, Response response) {
	
		StringBuilder stringBuilder = new StringBuilder();
		
	 	stringBuilder.append("<html>");
	    stringBuilder.append("<head><title>how to ...</title></head>");
	    stringBuilder.append("<body bgcolor=white>");
	    stringBuilder.append("<table border=\"0\">");
	    stringBuilder.append("<tr>");
	    stringBuilder.append("<td>");
	    stringBuilder.append("<h3>available REST calls</h3>");
	    stringBuilder.append("<ol><li>/rest/todos --> returns all TODOS</li>");
	    stringBuilder.append("<li>/rest/todos/{todoId} --> returns TODO by id</li>");
	    stringBuilder.append("<li>/rest/todos --> post a json object here to store a new TODO</li>");
	    stringBuilder.append("<li>/rest/todos --> put a json object here to update an existing TODO</li>");
	    stringBuilder.append("</ol>");
	    stringBuilder.append("</td>");
	    stringBuilder.append("</tr>");
	    stringBuilder.append("</table>");
	    stringBuilder.append("</body>");
	    stringBuilder.append("</html>");
	
	    response.setEntity(new StringRepresentation( stringBuilder.toString(), MediaType.TEXT_HTML));
	}
}
