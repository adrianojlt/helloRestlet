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
        INSTANCE.create(new Todo(1L, "Learn AngularJS", "For SPA simple page apps"));
        INSTANCE.create(new Todo(2L, "Use Bootstrap", "responsive css framework"));
        INSTANCE.create(new Todo(3L, "Restlet", "Build and and deploy a restful server using Restlet framework"));
        INSTANCE.create(new Todo(4L, "Git", "Master this Version Control System tool"));
        INSTANCE.create(new Todo(5L, "Jenkins", "For continuous integrate the source code created"));
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
