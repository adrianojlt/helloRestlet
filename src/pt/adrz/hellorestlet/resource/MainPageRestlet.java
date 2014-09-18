package pt.adrz.hellorestlet.resource;

import org.restlet.Response;
import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;

public class MainPageRestlet extends Restlet {

	@Override
	public void handle(Request request, Response response) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
	 	stringBuilder.append("<html>");
        stringBuilder.append("<head><title>Sample Application Servlet Page</title></head>");
        stringBuilder.append("<body bgcolor=white>");
        stringBuilder.append("<table border=\"0\">");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td>");
        stringBuilder.append("<h3>available REST calls</h3>");
        stringBuilder.append("<ol><li>/sys --> returns system up and date string</li>");
        stringBuilder.append("<li>/all/maps --> returns a list of all the cities and states</li>");
        stringBuilder.append("<li>/{key}/maps --> returns a list of cities for a particular state (CA,IL,TX)<br/>");
        stringBuilder.append("using one of the keys from the \"all\" " + "call<br/> pasted in place as the {key}.</li>");
        stringBuilder.append("<li>/xml --> POST or GET URL or web form elements as XML to this</li>");
        stringBuilder.append("</ol>");
        stringBuilder.append("</td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        response.setEntity(new StringRepresentation( stringBuilder.toString(), MediaType.TEXT_HTML));
	}
}
