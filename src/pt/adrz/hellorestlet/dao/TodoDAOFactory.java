package pt.adrz.hellorestlet.dao;

import java.util.List;

import pt.adrz.helloservlet.model.Todo;

public abstract class TodoDAOFactory {
	
	public static final int TODO_STATIC 			= 1;
	public static final int TODO_CACHE 				= 2;
	public static final int TODO_MYSQL_JDBC 		= 3;
	public static final int TODO_MYSQL_SPRING_JDBC 	= 4;
	//public static final int MYSQL_JPA 			= 5;
	//public static final int MYSQL_HIBERNATE 		= 6;
	
	public abstract List<Todo> list();
	public abstract Todo get(Long id);
	public abstract void create(Todo todo);
	public abstract void update(Todo todo);
	public abstract boolean delete(Long id);
	
	public static TodoDAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
			case TODO_STATIC: 				return new TodoStaticDAO();
			case TODO_CACHE: 				return null;
			case TODO_MYSQL_JDBC: 			return null;
			case TODO_MYSQL_SPRING_JDBC: 	return null;
			default: return null;
		}
	}
}
