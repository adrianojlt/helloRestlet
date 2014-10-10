package com.pmonteiro.server.models;

import java.util.Collection;

public class Privilege {

	private String name;
	private String description;
	
	Collection<RoleServer> userRoles;
}
