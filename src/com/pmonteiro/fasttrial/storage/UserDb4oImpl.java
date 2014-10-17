package com.pmonteiro.fasttrial.storage;

import java.util.List;

import com.pmonteiro.fasttrial.model.accounts.UserAccount;

public class UserDb4oImpl extends FactoryUser {

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
	public void tmp() {
		
	}

	@Override
	public UserAccount getTmpUser() {
		return null;
	}
}
