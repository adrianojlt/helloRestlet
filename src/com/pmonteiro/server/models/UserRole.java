package com.pmonteiro.server.models;

import java.util.Collection;

public class UserRole {

	private String name;
	private String description;
	
	private UserRole children;

	Collection<Privilege> privileges;
	
	public UserRole() {
		
	}
	
	public static UserRole getInstance() {
		return new UserRole();
	}
}
