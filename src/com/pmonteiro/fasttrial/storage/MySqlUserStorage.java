package com.pmonteiro.fasttrial.storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.pmonteiro.fasttrial.model.User;

public class MySqlUserStorage extends StorageFactory<User> {
	
	private static final String QUERY_ALL_USERS = 
			"";
	
	private static MySqlUserStorage storage = null;
	
	public static synchronized MySqlUserStorage getUserStorage() {
		if ( MySqlUserStorage.storage == null) { return new MySqlUserStorage(); }
		return MySqlUserStorage.storage;
	}

	public MySqlUserStorage() { }
	
	@SuppressWarnings("unused")
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		return ConnectionFactory.getInstance().getConnection();
	}


	@Override
	public List<User> list() {
		return null;
	}

	@Override
	public User get(Long id) {
		return null;
	}

	@Override
	public User get(String email) {
		return null;
	}

	@Override
	public void create(User user) {
		
	}

	@Override
	public void update(User user) {
		
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}
}
