package pt.adrz.hellorestlet.dao.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

import pt.adrz.hellorestlet.model.Todo;

public class TodoStaticDAO  extends TodoDAOFactory {
	

	private static final Map<Integer, Todo> 	REPOSITORY = new ConcurrentSkipListMap<>(); 
	private static final AtomicInteger 		IDS = new AtomicInteger(); 
	private static final TodoStaticDAO 		INSTANCE = new TodoStaticDAO();
	
	static {
        INSTANCE.create(new Todo(1, "Learn AngularJS", "For SPA simple page apps"));
        INSTANCE.create(new Todo(2, "Use Bootstrap", "responsive css framework"));
        INSTANCE.create(new Todo(3, "Restlet", "Build and and deploy a restful server using Restlet framework"));
        INSTANCE.create(new Todo(4, "Git", "Master this Version Control System tool"));
        INSTANCE.create(new Todo(5, "Jenkins", "For continuous integrate the source code created"));
    }
	
	public static TodoStaticDAO getInstance() {
		return INSTANCE;
	}
	
	@Override
	public List<Todo> list() {
		return new ArrayList<>(REPOSITORY.values());
	}

	@Override
	public Todo get(Integer id) {
		return REPOSITORY.get(id);
	}

	@Override
	public void create(Todo todo) {
		 int id = IDS.getAndIncrement();
		 todo.setId(id);
		 REPOSITORY.put(id, todo);
	}

	@Override
	public void update(Todo todo) {
		REPOSITORY.put(todo.getId(), todo);
	}

	@Override
	public boolean delete(Integer id) {
		return REPOSITORY.remove(id) != null;
	}

}
