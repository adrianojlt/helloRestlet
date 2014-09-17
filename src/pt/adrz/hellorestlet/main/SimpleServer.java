package pt.adrz.hellorestlet.main;

import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.Response;
import org.restlet.Server;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;

public class SimpleServer {

	public static void main(String[] args) throws Exception{
		
		Restlet restlet = new Restlet() {

			@Override
			public void handle(Request request, Response response) {
				response.setEntity("hello SimpleServer!!!", MediaType.TEXT_PLAIN);
			}
		};
		
		new Server(Protocol.HTTP,8111,restlet).start();
	}
}
