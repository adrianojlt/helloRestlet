package pt.adrz.hellorestlet.dao;

import java.util.List;
import pt.adrz.helloservlet.model.Todo;

public interface TodoDAO {
	
	public List<Todo> list();
	public Todo get(Long id);

	public void create(Todo todo);
	public void update(Todo todo);
	public void delete(Long id);
}
