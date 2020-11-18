package com.travelfactory.javadevelopertest.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelfactory.javadevelopertest.dto.CampaignData;
import com.travelfactory.javadevelopertest.exception.ResourceNotFoundException;
import com.travelfactory.javadevelopertest.model.Campaign;
import com.travelfactory.javadevelopertest.repository.CampaignRepository;

@RestController
@RequestMapping("/marketing/ws/partner/campaign")
public class CampaignController {
	@Autowired
	private CampaignRepository campaignRepository;
	
	@GetMapping("/all")
	public List<Campaign> getAllCampaigns() {
		return campaignRepository.findAll();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<CampaignData> getCampaignById(@PathVariable(value = "id") Long campaignId) 
    		throws ResourceNotFoundException {
		Campaign campaign = campaignRepository.findById(campaignId)
				.orElseThrow(() -> new ResourceNotFoundException("Campaign not found for this id :: " + campaignId));
		CampaignData campaignData = new CampaignData(campaign);
		return ResponseEntity.ok().body(campaignData);
    }
	
	@PostMapping("/new")
    public CampaignData createCampaign(@RequestBody Campaign campaign) {
		Campaign campaignSaved = campaignRepository.save(campaign);
        return new CampaignData(campaignSaved);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<CampaignData> updateCampaign(@PathVariable(value = "id") Long campaignId, 
			@RequestBody Campaign campaignDetails) throws ResourceNotFoundException {
		Campaign campaign = campaignRepository.findById(campaignId)
				.orElseThrow(() -> new ResourceNotFoundException("Campaign not found for this id :: " + campaignId));

	    campaign.setCampaignName(campaignDetails.getCampaignName());
	    campaign.setMandatoryFields(campaignDetails.getMandatoryFields());
	    final Campaign updatedCampaign = campaignRepository.save(campaign);
	    CampaignData campaignData = new CampaignData(updatedCampaign);
	    return ResponseEntity.ok(campaignData);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteCampaign(@PathVariable(value = "id") Long campaignId)
			throws ResourceNotFoundException {
		Campaign campaign = campaignRepository.findById(campaignId)
				.orElseThrow(() -> new ResourceNotFoundException("Campaign not found for this id :: " + campaignId));
		
		campaignRepository.delete(campaign);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
