package pt.adrz.hellorestlet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import pt.adrz.helloservlet.model.Todo;

public class TodoStaticDAO  extends TodoDAOFactory {
	

	private static final Map<Long, Todo> 	REPOSITORY = new ConcurrentSkipListMap<>(); 
	private static final AtomicLong 		IDS = new AtomicLong(1); 
	private static final TodoStaticDAO 		INSTANCE = new TodoStaticDAO();
	
	static {
        INSTANCE.create(new Todo(1L, "Learn AngularJS", "HTML is great for declaring static documents, but it falters when we try to use it for declaring dynamic views in web-applications. AngularJS lets you extend HTML vocabulary for your application. The resulting environment is extraordinarily expressive, readable, and quick to develop. "));
        INSTANCE.create(new Todo(2L, "Use Twitter Bootstrap", "Sleek, intuitive, and powerful mobile-first front-end framework for faster and easier web development."));
        INSTANCE.create(new Todo(3L, "Integrate with Restlet", "The leading web API framework for Java"));
    }
	
	public static TodoStaticDAO getInstance() {
		return INSTANCE;
	}
	
	@Override
	public List<Todo> list() {
		return new ArrayList<>(REPOSITORY.values());
	}

	@Override
	public Todo get(Long id) {
		return REPOSITORY.get(id);
	}

	@Override
	public void create(Todo todo) {
		 long id = IDS.getAndIncrement();
		 todo.setId(id);
		 REPOSITORY.put(id, todo);
	}

	@Override
	public void update(Todo todo) {
		REPOSITORY.put(todo.getId(), todo);
	}

	@Override
	public boolean delete(Long id) {
		return REPOSITORY.remove(id) != null;
	}

}
