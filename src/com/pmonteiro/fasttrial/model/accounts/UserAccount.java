package com.pmonteiro.fasttrial.model.accounts;

import java.util.Collection;
import java.util.Date;

import com.pmonteiro.fasttrial.model.Status;
import com.pmonteiro.fasttrial.model.Study;

public class UserAccount extends UserRole {

	private Long id;
	private String username;
	private String email;
	private String fullName;
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
	private UserType userType;
	
	private Collection<Study> studies;
	
	public UserAccount() {
		
	}
	
	//public UserAccount(String username) { }
}