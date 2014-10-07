package com.pmonteiro.server.models;

import java.util.Date;

public class UserAccount {

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
	
	//public UserAccount(String username) { }
}
