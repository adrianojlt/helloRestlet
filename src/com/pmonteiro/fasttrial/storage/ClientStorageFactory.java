package com.pmonteiro.fasttrial.storage;

import java.util.List;

import com.pmonteiro.fasttrial.model.Client;

import pt.adrz.hellorestlet.dao.DataType;

public abstract class ClientStorageFactory {

	enum STORAGE_TYPE { STATIC,CACHE,MYSQL_JDBC,MYSQL_SPRING_JDBC }
	
	public abstract List<Client> list();
	public abstract Client get(Long id);
	public abstract Client get(String email);
	public abstract void create(Client todo);
	public abstract void update(Client todo);
	public abstract boolean delete(Long id);
	
	public static ClientStorageFactory getStorageFactory(DataType whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return null;
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return null;
			case MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}
	}
}
