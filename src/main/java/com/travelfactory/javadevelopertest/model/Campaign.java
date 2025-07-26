package com.travelfactory.javadevelopertest.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "campaigns")
public class Campaign {
	private long id;
	private String campaignName;
	private List<String> mandatoryFields;
	private Set<Contact> contacts = new HashSet<Contact>();
	
	public Campaign() {
		
	}

	public Campaign(long id, String campaignName, List<String> mandatoryFields) {
		this.id = id;
		this.campaignName = campaignName;
		this.mandatoryFields = mandatoryFields;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "campaign_name", nullable = false)
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	@Column(name = "mandatory_fields")
	@ElementCollection(targetClass=String.class)
	public List<String> getMandatoryFields() {
		return mandatoryFields;
	}
	public void setMandatoryFields(List<String> mandatoryFields) {
		this.mandatoryFields = mandatoryFields;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campaign")
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
}
