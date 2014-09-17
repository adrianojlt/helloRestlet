package pt.adrz.hellorestlet.resource;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class RootResource extends ServerResource{

	 /**
     * Constructor disabling content negotiation and indicating if the
     * identified resource already exists.
     */
    public RootResource() {
        setNegotiated(false);
        // setExisting(false);
    }
    
    @Get
    public Representation represent() {
		Representation representation = new StringRepresentation("hello mail",MediaType.TEXT_PLAIN);
		return representation;
        //return "hello, world";
    }

    @Override
    protected void doInit() throws ResourceException {
        System.out.println("The root resource was initialized.");
    }

    @Override
    protected void doCatch(Throwable throwable) {
        System.out.println("An exception was thrown in the root resource.");
    }

    @Override
    protected void doRelease() throws ResourceException {
        System.out.println("The root resource was released.\n");
    }

    /**
     * Handle the HTTP GET method by returning a simple textual representation.
     */
    @Override
    protected Representation get() throws ResourceException {
        System.out.println("The GET method of root resource was invoked.");
        return new StringRepresentation("This is the root resource");
    }

    /**
     * Handle the HTTP OPTIONS method by illustrating the impact of throwing an
     * exception.
     */
    @Override
    protected Representation options() throws ResourceException {
        System.out.println("The OPTIONS method of root resource was invoked.");
        throw new RuntimeException("Not yet implemented");
    }
}
