package com.travelfactory.javadevelopertest.dto;

import com.travelfactory.javadevelopertest.model.Campaign;
import com.travelfactory.javadevelopertest.model.Contact;

public class ContactData {
	private long id;
	private String name;
	private String firstName;
	private String email;
	private String phone;
	private long campaignId;
	
	public ContactData() {
		
	}
	
	public ContactData(Contact contact) {
		this.id = contact.getId();
		this.name = contact.getName();
		this.firstName = contact.getFirstName();
		this.email = contact.getEmail();
		this.phone = contact.getPhone();
		
		Campaign campaign = contact.getCampaign();
		if (campaign != null) {
			this.campaignId = campaign.getId();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	
	
}
