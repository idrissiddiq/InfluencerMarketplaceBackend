package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.request.CreateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.request.UpdateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.response.EmployeeRequest;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/campaign")
public class CampaignController {

    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public ResponseListData<Campaign> findAll() {
        return new ResponseListData(campaignService.findAll());
    }

    @GetMapping("/me")
    public ResponseListData<Campaign> findAllByBrand(Authentication authentication) {
        return new ResponseListData(campaignService.findAllByBrand(authentication));
    }

    @GetMapping("/search/{id}")
    public ResponseData<Influencer> findById(@PathVariable Long id) {
        return new ResponseData(campaignService.findById(id));
    }


    @GetMapping("/{id}")
    public ResponseListData<Campaign> findAllByStatus(@PathVariable Long id) {
        return new ResponseListData(campaignService.findAllByStatus(id));
    }

    @GetMapping("/me/{id}")
    public ResponseListData<Campaign> findAllByBrandStatus(Authentication authentication, @PathVariable Long id) {
        return new ResponseListData(campaignService.findAllByBrandStatus(authentication, id));
    }

    @PostMapping("/create")
    public ResponseMessage<CreateCampaignRequest> createCampaign(@RequestBody CreateCampaignRequest request) {
        return campaignService.createCampaign(request);
    }

    @PutMapping("/me/{id}")
    public String updateCampaign(@RequestBody UpdateCampaignRequest request, Authentication authentication, @PathVariable Long id){
        return campaignService.updateCampaign(request, authentication, id);
    }
}
