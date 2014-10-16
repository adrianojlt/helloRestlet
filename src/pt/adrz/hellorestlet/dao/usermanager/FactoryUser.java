package pt.adrz.hellorestlet.dao.usermanager;

import java.sql.SQLException;
import java.util.List;

import com.pmonteiro.fasttrial.storage.StorageType;

import pt.adrz.hellorestlet.model.Client;
import pt.adrz.hellorestlet.model.User;

public abstract class FactoryUser {
	
	public abstract List<User> list();
	public abstract User get(Long id);
	public abstract User get(User user);
	public abstract User get(String email);
	public abstract boolean exists(User user);
	public abstract boolean create(User user) throws SQLException, Exception;
	public abstract void update(User user);
	public abstract boolean delete(Long id);
	public abstract List<Client> listClients(Long userId);
	public abstract void tmp();
	
	public static FactoryUser getUserStorage(StorageType whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return new MySqlImplUser();
			case MYSQL_SPRING_JDBC: 	return null;
			case DB4O: 					return new Db4oImplUser();
			default: return null;
		}	
	}

}
