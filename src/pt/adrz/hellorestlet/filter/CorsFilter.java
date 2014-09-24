package pt.adrz.hellorestlet.filter;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.engine.header.Header;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.routing.Filter;
import org.restlet.util.Series;

public class CorsFilter extends Filter{
	
	public CorsFilter(Context context) {
		super(context);
	}
	
	@Override
	protected int beforeHandle(Request request, Response response) {
		
		//Form requestHeaders = (Form)request.getAttributes().get("org.restlet.http.headers");
		
		// only on 2.0
		//Form responseHeaders = (Form) response.getAttributes().get("org.restlet.http.headers");
		
		if ( Method.OPTIONS.equals(request.getMethod()) ) {
		
			@SuppressWarnings("unchecked")
			Series<Header> responseHeaders = (Series<Header>)response.getAttributes().get("org.restlet.http.headers");
		  
			if (responseHeaders == null) { 
				responseHeaders = new Series<>(Header.class);
				response.getAttributes().put("org.restlet.http.headers",responseHeaders); 
			}
			
			responseHeaders.add("Access-Control-Allow-Origin", "*");
			responseHeaders.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
	 		responseHeaders.add("Access-Control-Allow-Headers", "Content-Type");
	 		responseHeaders.add("Access-Control-Allow-Credentials", "true");
	 		responseHeaders.add("Access-Control-Max-Age", "60");
	 		
	 		response.setEntity(new EmptyRepresentation());
			
			return Filter.SKIP;
		}
		
		return super.beforeHandle(request, response);
	}
	
	@Override
	protected void afterHandle(Request request, Response response) {

		if ( !Method.OPTIONS.equals(request.getMethod()) ) {
		
			//Form responseHeaders = (Form) response.getAttributes().get("org.restlet.http.headers");
			@SuppressWarnings("unchecked")
			Series<Header> responseHeaders = (Series<Header>)response.getAttributes().get("org.restlet.http.headers");
			  
			if (responseHeaders == null) { 
				responseHeaders = new Series<>(Header.class);
				response.getAttributes().put("org.restlet.http.headers",responseHeaders); 
			}
			
			responseHeaders.add("Access-Control-Allow-Origin", "*");
			responseHeaders.add("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
	 		responseHeaders.add("Access-Control-Allow-Headers", "Content-Type");
	 		responseHeaders.add("Access-Control-Allow-Credentials", "true");
	 		responseHeaders.add("Access-Control-Max-Age", "60");
	 		
			super.afterHandle(request, response);
		}
	}
}
