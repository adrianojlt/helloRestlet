package com.pmonteiro.fasttrial.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pmonteiro.server.models.UserServer;
import com.pmonteiro.server.models.RoleServer;

public class Study {
	
	private String name;
	private String uniqueIdentifier;
	private String secondaryIdentifier;
	private String summary;
	
	private Integer updateId;

	private Date datePlannedStart;
	private Date datePlannedEnd;
	private Date dateCreated;
	private Date dateUpdated;
	
	private String principalInvestigator;
	private String facilityName;
	private String facilityCity;
	private String facilityState;
	private String facilityZip;
	private String facilityCountry;
	private String facilityRecruitmentStatus;
	private String facilityContactName;
	private String facilityContactDegree;
	private String facilityContactPhone;
	private String facilityContactEmail;
	private String protocolType;
	private String protocolDescription;
	private Date protocolDateVerification;
	private String phase;
	private Integer expectedTotalEnrollment;
	private String sponsor;
	private String collaborators;
	private String medlineIdentifier;
	private String url;
	private String urlDescription;
	private String conditions;
	private String keywords;
	private String eligibility;
	private String gender;
	private String ageMax;
	private String ageMin;
	private Boolean healthyVolunteerAccepted;
	private String purpose;
	private String allocation;
	private String masking;
	private String control;
	private String assignment;
	private String endpoint;
	private String interventions;
	private String duration;
	private String selection;
	private String timing;
	private String officialTitle;
	private Boolean resultsReference;
	private String oc_oid;
	
	
	private Study parent;
	private UserServer owner;
	private List<CRF> crfs;
	private Map<UserServer, RoleServer> users;
	
	public Study() {
		this.users = new HashMap<UserServer,RoleServer>();
	}

	public UserServer getUsers(RoleServer role) {
		// users.
		return null;
	}

}
