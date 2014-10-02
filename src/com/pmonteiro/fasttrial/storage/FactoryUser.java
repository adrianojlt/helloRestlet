package com.pmonteiro.fasttrial.storage;

import java.sql.SQLException;
import java.util.List;

import com.pmonteiro.fasttrial.model.Client;
import com.pmonteiro.fasttrial.model.User;

public abstract class FactoryUser {
	
	public abstract List<User> list();
	public abstract User get(Long id);
	public abstract User get(String email);
	public abstract boolean create(User user) throws SQLException, Exception;
	public abstract void update(User user);
	public abstract boolean delete(Long id);
	public abstract List<Client> listClients(Long userId);
	
	public static FactoryUser getUserStorage(StorageType whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return new MySqlImplUser();
			case MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}	
	}

}
