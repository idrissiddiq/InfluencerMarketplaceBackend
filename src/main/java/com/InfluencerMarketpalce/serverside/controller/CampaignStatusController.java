package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.CampaignStatus;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.CampaignStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/campaignStatus")
public class CampaignStatusController {
    private CampaignStatusService campaignStatusService;

    @Autowired
    public CampaignStatusController(CampaignStatusService campaignStatusService) {
        this.campaignStatusService = campaignStatusService;
    }

    @GetMapping
    public ResponseListData<CampaignStatus> findAll() {
        return new ResponseListData(campaignStatusService.findAll());
    }
}
