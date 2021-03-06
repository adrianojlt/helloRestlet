package pt.adrz.hellorestlet.dao.todo;

import java.util.List;

import pt.adrz.hellorestlet.model.Todo;

public interface TodoDAO {
	
	public List<Todo> list();
	public Todo get(Long id);

	public void create(Todo todo);
	public void update(Todo todo);
	public void delete(Long id);
}
