package com.pmonteiro.fasttrial.storage;

import java.util.List;

import com.pmonteiro.fasttrial.model.accounts.UserAccount;
import com.pmonteiro.fasttrial.storage.StorageType;

public abstract class FactoryUser {
	
	public abstract List<UserAccount> listAll();
	public abstract UserAccount get(UserAccount user);
	public abstract boolean exists(UserAccount user);
	public abstract boolean create(UserAccount user);
	public abstract void update(UserAccount user);
	public abstract boolean delete(UserAccount id);

	public abstract void tmp();
	public abstract UserAccount getTmpUser();
	
	public static FactoryUser getUserStorage(StorageType whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return new UserCacheImpl();
			case MYSQL_JDBC: 			return null;
			case MYSQL_SPRING_JDBC: 	return null;
			case DB4O: 					return null;
			default: return null;
		}	
	}

}
