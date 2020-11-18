package com.travelfactory.javadevelopertest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelfactory.javadevelopertest.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	List<Contact> findContactByCampaignId(Long campaignId);
}
