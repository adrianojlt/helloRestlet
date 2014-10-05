package pt.adrz.hellorestlet.dao;

import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;
import com.pmonteiro.fasttrial.storage.ConnectionFactory;

import pt.adrz.hellorestlet.model.Todo;

public class TodoMySqlDAO extends TodoDAOFactory {
	
	public TodoMySqlDAO() { }
	
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		return ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public List<Todo> list() {
		return null;
	}

	@Override
	public Todo get(Integer id) {
		return null;
	}

	@Override
	public void create(Todo todo) {
		
	}

	@Override
	public void update(Todo todo) {
		
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

}
