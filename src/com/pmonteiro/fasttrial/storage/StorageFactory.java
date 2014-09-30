package com.pmonteiro.fasttrial.storage;

import java.util.List;

import com.pmonteiro.fasttrial.model.Client;
import com.pmonteiro.fasttrial.model.User;

public abstract class StorageFactory<T> {

	public static enum STORAGE_TYPE { STATIC,CACHE,MYSQL_JDBC,MYSQL_SPRING_JDBC }
	
	public abstract List<T> list();
	public abstract T get(Long id);
	public abstract T get(String email);
	public abstract void create(T user);
	public abstract void update(T user);
	public abstract boolean delete(Long id);
	
	public static StorageFactory<User> getUserStorage(STORAGE_TYPE whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return MySqlUserStorage.getUserStorage();
			case MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}
	}
	
	public static StorageFactory<Client> getClientStorage(STORAGE_TYPE whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return MySqlClientStorage.getClientStorage(whichFactory);
			case MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}
	}
}
