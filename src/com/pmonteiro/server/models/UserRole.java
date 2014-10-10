package com.pmonteiro.server.models;

import java.util.Collection;
import org.restlet.security.*;

public class UserRole extends Role{
	
	public enum ROLES {}

	private String name;
	private String description;
	
	private UserRole children;

	Collection<Privilege> privileges;
	
	public UserRole() { }
	
	public static UserRole getInstance() {
		return new UserRole();
	}
}
