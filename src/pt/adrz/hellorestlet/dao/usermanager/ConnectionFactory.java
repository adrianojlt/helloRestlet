package pt.adrz.hellorestlet.dao.usermanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	
	private static ConnectionFactory instance = new ConnectionFactory();
	
	private String url = "jdbc:mysql://localhost/hellorestlet";
	private String driverClass = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String pass = "";
	
	private ConnectionFactory() {
		try { Class.forName(driverClass); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
	}
	
	public static ConnectionFactory getInstance() { return instance; }
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = DriverManager.getConnection(url,user,pass);
		return conn;
	}
	
	public static void close(ResultSet rs) {
		if ( rs == null) return;
		try { rs.close(); }
		catch (SQLException eSQL) {}
	}

	public static void close(Statement st) {
		if ( st  == null) return;
		try { st.close(); }
		catch (SQLException eSQL) {}
	}
	
	public static void close(PreparedStatement ps) {
		if ( ps == null) return;
		try { ps.close(); }
		catch (SQLException eSQL) {}
	}

	public static void close(Connection conn) {
		if ( conn == null) return;
		try { conn.close(); }
		catch (SQLException eSQL) {}
	}
	
	public static void close(ResultSet rs, Statement st, Connection conn) {
		close(rs);
		close(st);
		close(conn);
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		close(rs);
		close(ps);
		close(conn);
	}
	
	public static void close(ResultSet rs, Statement st, PreparedStatement ps, Connection conn) {
		close(rs);
		close(st);
		close(ps);
		close(conn);
	}
}
