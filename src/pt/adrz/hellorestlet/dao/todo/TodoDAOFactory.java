package pt.adrz.hellorestlet.dao.todo;

import java.util.List;

import pt.adrz.hellorestlet.dao.DataType;
import pt.adrz.hellorestlet.model.Todo;

public abstract class TodoDAOFactory {
	
	enum DAO_TYPE { STATIC,CACHE,MYSQL_JDBC,MYSQL_SPRING_JDBC }
	
	public abstract List<Todo> list();
	public abstract Todo get(Integer id);
	public abstract void create(Todo todo);
	public abstract void update(Todo todo);
	public abstract boolean delete(Integer id);
	
	public static TodoDAOFactory getDAOFactory(DataType whichFactory) {

		switch (whichFactory) {
			case STATIC: 				return new TodoStaticDAO();
			case CACHE: 				return null;
			case MYSQL_JDBC: 			return new TodoMySqlDAO();
			case MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}
	}
}
