package com.pmonteiro.fasttrial.storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.pmonteiro.fasttrial.model.Client;
import com.pmonteiro.fasttrial.model.User;

public class MySqlClientStorage extends StorageFactory<Client> {
	
	private static final String QUERY_ALL_CLIENTS = 
			"";
	
	private static MySqlClientStorage storage = null;
	
	public static synchronized MySqlClientStorage getUserStorage() {
		if ( MySqlClientStorage.storage == null) { return new MySqlClientStorage(); }
		return MySqlClientStorage.storage;
	}
	
	public MySqlClientStorage() { }
	
	@SuppressWarnings("unused")
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		return ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public List<Client> list() {
		return null;
	}

	@Override
	public Client get(Long id) {
		return null;
	}

	@Override
	public Client get(String email) {
		return null;
	}

	@Override
	public boolean create(Client client) {
		return false;
	}

	@Override
	public void update(Client client) {
		
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}
}
