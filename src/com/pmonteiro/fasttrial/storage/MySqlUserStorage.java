package com.pmonteiro.fasttrial.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pt.adrz.hellorestlet.model.Client;
import pt.adrz.hellorestlet.model.User;

public class MySqlUserStorage extends StorageFactory<User> {
	
	private static final String QUERY_ALL_USERS = 
			"SELECT id, id_group, name, email FROM users;";

	private static final String QUERY_GET_BY_ID = 
			"SELECT id, id_group, name, email FROM users WHERE id = ?;";
	
	// singleton ...
	private static MySqlUserStorage storage = null;
	
	public static synchronized MySqlUserStorage getUserStorage() {
		if ( MySqlUserStorage.storage == null) { return new MySqlUserStorage(); }
		return MySqlUserStorage.storage;
	}

	public MySqlUserStorage() { }
	
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		return ConnectionFactory.getInstance().getConnection();
	}


	@Override
	public List<User> list() {
		
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {

			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(MySqlUserStorage.QUERY_ALL_USERS);

			while ( rs.next() ) { 

				User user = new User();
				user.setId((rs.getLong("id")));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				
				users.add(user);
			}
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (Exception e) { e.printStackTrace();}
		finally { ConnectionFactory.close(rs, st, conn); }
		
		return users;
	}

	@Override
	public User get(Long id) {
		
		User user = new User();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = getConnection();
			ps = conn.prepareStatement(MySqlUserStorage.QUERY_GET_BY_ID);
			ps.setLong(1,id);	
			ps.setMaxRows(1);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setGroupId(rs.getLong("id_group"));
			}
			else { 
				return null;
			}
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (NullPointerException eNull) { }
		catch (Exception e) { e.printStackTrace(); }
		finally { ConnectionFactory.close(rs, ps, conn); }

		return user;
	}

	@Override
	public User get(String email) {
		return null;
	}

	@Override
	public boolean create(User user) throws SQLException, Exception {
		return false;
	}

	@Override
	public void update(User user) {
		
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}
}
