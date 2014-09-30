package com.pmonteiro.server.web;

public class Route {
	
	private String path, packageName, className;
	
	public Route(String path, String packageName, String className) {
		this.path = path;
		this.packageName = packageName;
		this.className = className;
	}

	public String getPath() { return path; } 
	public void setPath(String path) { this.path = path; } 
	public String getPackageName() { return packageName; } 
	public void setPackageName(String packageName) { this.packageName = packageName; } 
	public String getClassName() { return className; } 
	public void setClassName(String className) { this.className = className; }
}
