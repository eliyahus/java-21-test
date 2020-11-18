package com.travelfactory.javadevelopertest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.travelfactory.javadevelopertest.model.Campaign;
import com.travelfactory.javadevelopertest.model.Contact;

public class CampaignData {
	private long id;
	private String campaignName;
	private List<String> mandatoryFields;
	private List<Long> contactIds;
	
	public CampaignData() {
		
	}
	
	public CampaignData(Campaign campaign) {
		this.id = campaign.getId();
		this.campaignName = campaign.getCampaignName();
		this.mandatoryFields = campaign.getMandatoryFields();
		
		this.contactIds = new ArrayList<>();
		Set<Contact> contacts = campaign.getContacts();
		if (contacts != null) {
			for (Contact contact : contacts) {
				contactIds.add(contact.getId());
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public List<String> getMandatoryFields() {
		return mandatoryFields;
	}

	public void setMandatoryFields(List<String> mandatoryFields) {
		this.mandatoryFields = mandatoryFields;
	}

	public List<Long> getContactIds() {
		return contactIds;
	}

	public void setContactIds(List<Long> contactIds) {
		this.contactIds = contactIds;
	}
}
