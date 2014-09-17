package pt.adrz.hellorestlet.application;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;

public class FirstRestlet extends Restlet {
	
	@Override
	public void handle(Request request, Response response) {

		String entity = 
			      "Method: " 			+ request.getMethod() + 
				  "\nResource URI: " 	+ request.getResourceRef() + 
				  "\nIP addresss : " 	+ request.getClientInfo().getAddress() + 
				  "\nAgentName : " 		+ request.getClientInfo().getAgentName() + 
				  "\nPort : " 			+ request.getClientInfo().getPort();
		
		response.setEntity(entity, MediaType.TEXT_PLAIN);
	}
}
