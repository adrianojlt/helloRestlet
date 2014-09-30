package com.pmonteiro.fasttrial.storage;

import java.util.List;
import com.pmonteiro.fasttrial.model.User;
import pt.adrz.hellorestlet.dao.DataType;

public abstract class UserStorageFactory {

	enum STORAGE_TYPE { STATIC,CACHE,MYSQL_JDBC,MYSQL_SPRING_JDBC }
	
	public abstract List<User> list();
	public abstract User get(Long id);
	public abstract User get(String email);
	public abstract void create(User user);
	public abstract void update(User user);
	public abstract boolean delete(Long id);
	
	public static UserStorageFactory getStorageFactory(DataType whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return null;
			case MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}
	}
}
