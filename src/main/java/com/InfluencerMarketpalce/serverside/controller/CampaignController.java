package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.request.CreateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.request.UpdateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.response.*;
import com.InfluencerMarketpalce.serverside.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/table")
    public ResponseListData<FindAllOpenCampaignTableResponse> findAllOpenCampaignTable() {
        return new ResponseListData(campaignService.findAllOpenCampaignTable());
    }

//    @GetMapping("/me/{id}")
//    public ResponseListData<Campaign> findAllByBrandStatus(@PathVariable Long id) {
//        return new ResponseListData(campaignService.findAllByBrandStatus(id));
//    }
//
    @PostMapping("/create")
    public ResponsePostMessage createCampaign(@RequestBody Map<String, Object> param) {
        return campaignService.createCampaign(param);
    }
//
//    @PutMapping("/me/{id}")
//    public ResponseMessage updateCampaign(@RequestBody UpdateCampaignRequest request, @PathVariable Long id){
//        return campaignService.updateCampaign(request, id);
//    }
}
