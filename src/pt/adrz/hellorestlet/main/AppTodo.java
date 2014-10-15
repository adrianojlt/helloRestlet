package pt.adrz.hellorestlet.main;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import com.pmonteiro.server.web.ClassLoaderDirectory;
import com.pmonteiro.server.web.CompositeClassLoader;

import pt.adrz.hellorestlet.resource.todo.TodoResource;
import pt.adrz.hellorestlet.resource.todo.TodosResource;

public class AppTodo extends Application {


	@Override
	public Restlet createInboundRoot() {
		
		String resources = "clap://class/static/";

		Directory directory = new Directory(getContext(), resources);
	    directory.setListingAllowed(true);
	    directory.setDeeplyAccessible(true);
	    
	    /*
	    LocalReference localReference = LocalReference.createClapReference(LocalReference.CLAP_THREAD, "/src/com/pmonteiro/fasttrial/ui/");
        CompositeClassLoader compositeCL = new CompositeClassLoader();
        compositeCL.addClassLoader(Thread.currentThread().getContextClassLoader());
        compositeCL.addClassLoader(Router.class.getClassLoader());       
        ClassLoaderDirectory dir = new ClassLoaderDirectory(getContext(),localReference,compositeCL);
        */
	        
		Router todoRouter = new Router(this.getContext());
		
		todoRouter.attach("/web",directory);
		todoRouter.attach("/todos",TodosResource.class);
		todoRouter.attach("/todos/{todoId}",TodoResource.class);
		//todoRouter.attachDefault(TodosResource.class);

		return todoRouter;
	}
}
