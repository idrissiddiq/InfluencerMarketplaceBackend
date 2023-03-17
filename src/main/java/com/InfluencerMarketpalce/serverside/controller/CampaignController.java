package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.request.CreateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.request.UpdateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.response.FindAllOpenCampaignResponse;
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
    public ResponseListData<Campaign> findAllByBrand() {
        return new ResponseListData(campaignService.findAllByBrand());
    }

    @GetMapping("/search/{id}")
    public ResponseData<Influencer> findById(@PathVariable Long id) {
        return new ResponseData(campaignService.findById(id));
    }


    @GetMapping("/{id}")
    public ResponseListData<Campaign> findAllByStatus(@PathVariable Long id) {
        return new ResponseListData(campaignService.findAllByStatus(id));
    }

    @GetMapping("/open")
    public ResponseListData<FindAllOpenCampaignResponse> findAllOpenCampaign() {
        return new ResponseListData(campaignService.findAllOpenCampaign());
    }

    @GetMapping("/me/{id}")
    public ResponseListData<Campaign> findAllByBrandStatus(@PathVariable Long id) {
        return new ResponseListData(campaignService.findAllByBrandStatus(id));
    }

    @PostMapping("/create")
    public ResponseMessage<CreateCampaignRequest> createCampaign(@RequestBody CreateCampaignRequest request) {
        return campaignService.createCampaign(request);
    }

    @PutMapping("/me/{id}")
    public ResponseMessage updateCampaign(@RequestBody UpdateCampaignRequest request, @PathVariable Long id){
        return campaignService.updateCampaign(request, id);
    }
}
