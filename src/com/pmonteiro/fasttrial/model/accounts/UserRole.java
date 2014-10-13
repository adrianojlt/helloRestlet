package com.pmonteiro.fasttrial.model.accounts;

import java.util.Collection;

public class UserRole {

	public enum ROLES {}

	private String name;
	private String description;
	
	private UserRole children;
	private Collection<Privilege> privileges;
	
	public UserRole() { }
	
	public static UserRole getInstance() {
		return new UserRole();
	}
}
