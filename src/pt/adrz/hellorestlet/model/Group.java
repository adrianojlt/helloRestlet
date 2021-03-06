package pt.adrz.hellorestlet.model;

import java.util.List;

public class Group {
	
	private Long id;
	private String name;
	private List<User> users;
	
	public Group(Long id, String name, List<User> users) {
		this.id = id;
		this.name = name;
		this.users = users;
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public List<User> getUsers() { return users; }
	public void setUsers(List<User> users) { this.users = users; }
}
