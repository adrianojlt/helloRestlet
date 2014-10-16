package pt.adrz.hellorestlet.dao.usermanager;

import java.sql.SQLException;
import java.util.List;

import com.pmonteiro.fasttrial.storage.StorageType;

import pt.adrz.hellorestlet.model.Client;

public abstract class FactoryClient {
	
	public abstract List<Client> list();
	public abstract List<Client> list(Long userid);
	public abstract Client get(Long id);
	public abstract Client get(String email);
	public abstract boolean create(Client user) throws SQLException, Exception;
	public abstract void update(Client user);
	public abstract boolean delete(Long id);
	
	public static FactoryClient getClientStorage(StorageType whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return new MySqlImplClient();
			case MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}	
	}
}
