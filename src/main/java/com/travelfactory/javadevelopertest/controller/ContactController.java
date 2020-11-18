package com.travelfactory.javadevelopertest.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelfactory.javadevelopertest.dto.ContactData;
import com.travelfactory.javadevelopertest.exception.InvalidFormatException;
import com.travelfactory.javadevelopertest.exception.ResourceNotFoundException;
import com.travelfactory.javadevelopertest.model.Campaign;
import com.travelfactory.javadevelopertest.model.Contact;
import com.travelfactory.javadevelopertest.repository.CampaignRepository;
import com.travelfactory.javadevelopertest.repository.ContactRepository;

@RestController
@RequestMapping("/marketing/ws/partner/campaign/{campaignId}")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private CampaignRepository campaignRepository;
	
	@GetMapping("/allcontacts")
	public List<ContactData> getAllCampaignContacts(@PathVariable(value = "campaignId") Long campaignId) {
		List<ContactData> contactDatas = new ArrayList<>();
		List<Contact> contacts = contactRepository.findContactByCampaignId(campaignId);
		for (Contact contact : contacts) {
			contactDatas.add(new ContactData(contact));
		}
		return contactDatas;
	}
	
	@GetMapping("/contact/{id}")
    public ResponseEntity<ContactData> getContactById(@PathVariable(value = "id") Long contactId) 
    		throws ResourceNotFoundException {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));
		ContactData contactData = new ContactData(contact);
		return ResponseEntity.ok().body(contactData);
    }
	
	@PostMapping("/registration")
	public ContactData createContact(@PathVariable(value = "campaignId") Long campaignId, @RequestBody Contact contact)
			throws ResourceNotFoundException, InvalidFormatException {
		Campaign campaign = campaignRepository.findById(campaignId)
				.orElseThrow(() -> new ResourceNotFoundException("Campaign not found for this id :: " + campaignId));
		List<String> mandatoryFields = campaign.getMandatoryFields();
		for (String field : mandatoryFields) {
			switch(field)
			{
			case "name":
				String name = contact.getName();
				if (name == null || name.length() == 0) {
					throw new InvalidFormatException("wrong format for field name");
				}
				break;
			case "firstName":
				String firstName = contact.getFirstName();
				if (firstName == null || firstName.length() == 0) {
					throw new InvalidFormatException("wrong format for field firstName");
				}
				break;
			case "email":
				String email = contact.getEmail();
				if (!validateEmail(email)) {
					throw new InvalidFormatException("wrong format for field email");
				}
				break;
			case "phone":
				String phone = contact.getPhone();
				if (!validatePhone(phone)) {
					throw new InvalidFormatException("wrong format for field phone");
				}
				break;
			}
		}
		contact.setCampaign(campaign);
		Contact contactSaved = contactRepository.save(contact);
		return new ContactData(contactSaved);
	}

	private boolean validatePhone(String phone) {
		if (phone == null || phone.length() == 0) {
			return false;
		}
		String pattern = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\ s\\./ 0-9]*$";
		return phone.matches(pattern);
	}

	private boolean validateEmail(String email) {
		if (email == null || email.length() == 0) {
			return false;
		}
		String pattern = "^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$";
		return email.matches(pattern);
	}
}
