package pt.adrz.hellorestlet.resource;

import org.restlet.Application;
import org.restlet.Restlet;

public class TmpRestlet extends Restlet{
	
	Application app;
	
	public TmpRestlet(Application app) {
		this.app = app;
	}

}
