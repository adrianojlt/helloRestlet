package pt.adrz.hellorestlet.resource;

import java.util.Calendar;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.Resource;
import org.restlet.resource.ServerResource;

public class FirstResource extends Resource {
	
	public FirstResource(Context context, Request request, Response response) {
        //super(context, request, response);
        // Here we add the representation variants exposed
        //getVariants().add(new Variant(MediaType.TEXT_PLAIN));
    }

	@Override
	public String getAttribute(String arg0) {
		return null;
	}

	@Override
	public Representation handle() { return null; }

	@Override
	public void setAttribute(String arg0, Object arg1) {
		
	}
	
	public Representation represent(Variant variant) {
		 String message = "hello!, the Demo " + "Service is available." + " Time of request is:"
	                + Calendar.getInstance()
	                .getTime().toString();
		 
		 return new StringRepresentation(message,MediaType.TEXT_PLAIN);
	}
}
