package com.travelfactory.javadevelopertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelfactory.javadevelopertest.model.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}
