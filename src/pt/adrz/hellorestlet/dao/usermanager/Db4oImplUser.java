package pt.adrz.hellorestlet.dao.usermanager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pt.adrz.hellorestlet.model.Client;
import pt.adrz.hellorestlet.model.User;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Db4oImplUser extends FactoryUser {
	
	private static final String DB_FILE = "hellorestlet.data";
	private ObjectContainer db = null;
	
	private List<User> users;
	private User user;

	private static Db4oImplUser storage = null;
	
	
	public Db4oImplUser() {
		this.db = Db4o.openFile(Db4oImplUser.DB_FILE);
	}
	

	@Override
	public List<User> list() {
		ObjectSet<User> result = db.get(User.class);
		List<User> lista = new ArrayList<User>();
		while (result.hasNext()) 
			lista.add(result.next());
		return lista;
	}

	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(User user) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Client> listClients(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean exists(User user) {
		return false;
	}


	@Override
	public void tmp() {
		ObjectSet os = this.db.get(User.class);
		this.users = new ArrayList<User>();
		while ( os.hasNext() ) { this.users.add((User)os.next()); }
	}
	
	

}
