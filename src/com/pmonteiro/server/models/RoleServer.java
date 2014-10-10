package com.pmonteiro.server.models;

import java.util.Collection;
import org.restlet.security.*;

public class RoleServer extends Role{
	
	public enum ROLES {}

	private String name;
	private String description;
	
	private RoleServer children;

	Collection<Privilege> privileges;
	
	public RoleServer() { }
	
	public static RoleServer getInstance() {
		return new RoleServer();
	}
}
