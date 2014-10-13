package com.pmonteiro.fasttrial.model.accounts;

import java.util.Collection;
import java.util.Date;

import org.restlet.security.User;

import com.pmonteiro.fasttrial.model.Status;
import com.pmonteiro.fasttrial.model.Study;

public class UserAccount extends User {
	
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

	private Collection<Study> studies;
	//private UserType userType;
	
	public UserAccount(String username, String secret) {
		super(username, secret.toCharArray());
		//this.setIdentifier(username);
		//this.setSecret(secret);
	}
}
