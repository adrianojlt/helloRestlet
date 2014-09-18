package pt.adrz.hellorestlet.dao;

import java.util.List;

import pt.adrz.helloservlet.model.Todo;

public class TodoMySqlDAO extends TodoDAOFactory {

	@Override
	public List<Todo> list() {
		return null;
	}

	@Override
	public Todo get(Long id) {
		return null;
	}

	@Override
	public void create(Todo todo) {
		
	}

	@Override
	public void update(Todo todo) {
		
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}

}
