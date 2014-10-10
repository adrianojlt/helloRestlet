package com.pmonteiro.fasttrial.model.accounts;

import java.util.Collection;

import com.pmonteiro.fasttrial.model.Study;
import com.pmonteiro.server.models.UserServer;

public class UserAccount extends UserServer {

	private Collection<Study> studies;
	private UserType userType;

}
