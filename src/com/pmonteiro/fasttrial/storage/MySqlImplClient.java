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

public class MySqlImplClient extends FactoryClient {
	
	private static final String FIELDS = 
			"id, id_user, name, email";
	
	private static final String QUERY_ALL_CLIENTS = 
			"SELECT " + FIELDS + " FROM clients;";

	private static final String QUERY_GET_CLIENTS_BY_ID = 
			"SELECT " + FIELDS + " FROM clients WHERE id = ?;";

	private static final String QUERY_GET_CLIENTS_BY_USER = 
			"SELECT " + FIELDS + " FROM clients WHERE id_user = ?;";

	private static final String QUERY_GET_CLIENTS_BY_EMAIL = 
			"SELECT " + FIELDS + " FROM clients WHERE email = ?;";
	
	private static MySqlImplClient storage = null;
	
	public static synchronized MySqlImplClient getClientStorage() {
		if ( MySqlImplClient.storage == null) { return new MySqlImplClient(); }
		return MySqlImplClient.storage;
	}
	
	public MySqlImplClient() { }
	
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		return ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public List<Client> list() {
		
		List<Client> clients = new ArrayList<Client>();
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {

			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(MySqlImplClient.QUERY_ALL_CLIENTS);

			while ( rs.next() ) { 

				Client client = new Client();

				client.setId((rs.getLong("id")));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				
				clients.add(client);
			}
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (Exception e) { e.printStackTrace();}
		finally { ConnectionFactory.close(rs, st, conn); }
		
		
		return clients;
	}

	@Override
	public List<Client> list(Long userid) {
		
		List<Client> clients = new ArrayList<Client>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			ps = conn.prepareStatement(MySqlImplClient.QUERY_GET_CLIENTS_BY_USER);
			ps.setLong(1,userid);	
			rs = ps.executeQuery();
			
			while ( rs.next() ) { 

				Client client = new Client();

				client.setId((rs.getLong("id")));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				
				clients.add(client);
			}
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (NullPointerException eNull) { eNull.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		finally { ConnectionFactory.close(rs, ps, conn); }

		return clients;
	}
	
	@Override
	public Client get(String email) {
		
		Client client = new Client();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			ps = conn.prepareStatement(MySqlImplClient.QUERY_GET_CLIENTS_BY_EMAIL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if ( rs.next() ) { 
				client.setId((rs.getLong("id")));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
			}
			else 
				client = null;
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (NullPointerException eNull) { eNull.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		finally { ConnectionFactory.close(rs, ps, conn); }

		return client;
	}

	@Override
	public Client get(Long id) {
		
		Client client = new Client();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			ps = conn.prepareStatement(MySqlImplClient.QUERY_GET_CLIENTS_BY_ID);
			ps.setLong(1,id);
			rs = ps.executeQuery();
			
			if ( rs.next() ) { 
				client.setId((rs.getLong("id")));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
			}
			else 
				client = null;
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (NullPointerException eNull) { eNull.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		finally { ConnectionFactory.close(rs, ps, conn); }

		return client;
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

	
	private List<Client> queryBy(String query, Long value) {
		return null;
	}
	private List<Client> queryBy(String query, String value) {
		return null;
	}
}
