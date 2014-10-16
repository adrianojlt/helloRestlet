package com.pmonteiro.fasttrial.storage;

import java.util.List;

import com.jalapeno.ApplicationContext;
import com.jalapeno.ObjectManager;
import com.pmonteiro.fasttrial.model.accounts.UserAccount;

public class CacheImplUser extends FactoryUser {
	
	public static ObjectManager objManager;
	
    public static final String URL = "jdbc:Cache://172.16.61.1:1972/USER";
    public static final String USER = "_SYSTEM";
    public static final String PWD = "pmonteiro";
    
    public CacheImplUser() {
    	try {
    		objManager = ApplicationContext.createObjectManager(URL, USER, PWD);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	@Override
	public List<UserAccount> listAll() {
		return null;
	}

	@Override
	public UserAccount get(UserAccount user) {
		return null;
	}

	@Override
	public boolean exists(UserAccount user) {
		return false;
	}

	@Override
	public boolean create(UserAccount user) {
		return false;
	}

	@Override
	public void update(UserAccount user) {
		
	}

	@Override
	public boolean delete(UserAccount id) {
		return false;
	}

	@Override
	public void tmp() { }

	@Override
	public UserAccount getTmpUser() {
		UserAccount user = null;
		try {
			user = objManager.openById(UserAccount.class, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
