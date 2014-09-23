package pt.adrz.hellorestlet.filter;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.routing.Filter;

public class Blocker extends Filter {
	
	private final Set<String> blockedAddresses;
	
	public Blocker(Context context) {

		super(context);
		this.blockedAddresses = new CopyOnWriteArraySet<String>();
	}
	
	protected int beforeHandle(Request request, Response response) {
		
		int result = Filter.STOP;
		
		if ( getBlockedAddresses().contains(request.getClientInfo().getAddress()) ) {
			response.setStatus(Status.CLIENT_ERROR_FORBIDDEN,"ip address blocked");
		}
		else {
			result = Filter.CONTINUE;
		}
		
		return result;
		
	}

	public Set<String> getBlockedAddresses() {
		return blockedAddresses;
	}
}
