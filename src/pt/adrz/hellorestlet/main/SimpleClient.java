package pt.adrz.hellorestlet.main;

import org.restlet.Client;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;

public class SimpleClient {

	public static void main(String[] args) throws Exception {

		String uri = (args.length > 0) ? args[0] : "http://localhost:8111" ;
	    //Client client = new Client(Protocol.HTTP);
	    ClientResource resource = new ClientResource(uri);
	    System.out.println("\n2) Describe the application\n");
        System.out.println(resource.options().getText());
	    //resource.get().write(System.out);
	    //client.get(uri).getEntity().write(System.out);
	}
}
