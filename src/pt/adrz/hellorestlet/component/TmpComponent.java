package pt.adrz.hellorestlet.component;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

import pt.adrz.hellorestlet.application.SimplePageApplication;

public class TmpComponent extends Component {
	
	public TmpComponent() {
		
		Server server = new Server(Protocol.HTTP,8111);

		this.getServers().add(server);

		this.getDefaultHost().attachDefault(new SimplePageApplication());
	}
}
