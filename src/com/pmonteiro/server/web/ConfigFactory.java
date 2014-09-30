package com.pmonteiro.server.web;

import java.util.List;

public abstract class ConfigFactory {
	
	static enum STORAGE_TYPE { STATIC, FILE, MYSQL, EMBEDDED}
	
	public abstract List<Route> getRoutes();
	
	public static ConfigFactory getConfigFactory(STORAGE_TYPE whichFactory) {
		
		switch (whichFactory) {
			case STATIC: 		return null;
			case FILE: 			return null;
			case MYSQL: 		return null;
			case EMBEDDED: 		return null;
			default: 			return null;
		}
	}
}
