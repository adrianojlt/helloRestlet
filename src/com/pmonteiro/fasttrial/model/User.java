package com.pmonteiro.fasttrial.model;

import java.util.List;

import org.json.JSONString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class User implements JSONString {
	
	private Long id;
	private String name;
	private String email;

	private Long groupId;
	//private Group group;
	private List<Client> clients;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public Long getGroupId() { return this.groupId; }
	public void setGroupId(Long id) { this.groupId = id; }

	//public Group getGroup() { return group; }
	//public void setGroup(Group group) { this.group = group; }

	public List<Client> getClients() { return clients; }
	public void setClients(List<Client> clients) { this.clients = clients; }

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toJSONString() {
		return null;
	}
}
