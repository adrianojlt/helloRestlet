package com.pmonteiro.server.models;

import java.util.Collection;
import java.util.Date;

import org.restlet.security.User;

import com.pmonteiro.fasttrial.model.Status;
import com.pmonteiro.fasttrial.model.Study;
import com.pmonteiro.fasttrial.model.accounts.UserType;

public class UserServer extends User {

	private Long id;
	private String username;
	private String password;
	private String passwdTimestamp;
	private String passwdChallengeQuestion;
	private String passwdChallengeAnswer;
	private String phone;

	private boolean enabled;
	private boolean locked;

	private Integer lockCounter;
	private Date dateCreated;
	private Date dateUpdated;
	private Date dateLastVisited;
	
	private Status statuses;
	
	public UserServer() {
		
	}
	
	//public UserAccount(String username) { }
}
