package com.pmonteiro.fasttrial.model;

import java.util.Date;

import com.pmonteiro.server.models.UserServer;

public class Section {

	private String label;
	private String title;
	private String subtitle;
	private String instructions;
	private String pageNumberLabel;
	private Integer ordinal;
	private Integer parentId;
	private Date dateCreated;
	private Date dateUpdated;
	private Integer updateId;
	private Integer borders;

	private CrfVersion crfVersion;
	private Status status;
	private UserServer userAccount;
	//private List<> itemFormMetadatas;

}
