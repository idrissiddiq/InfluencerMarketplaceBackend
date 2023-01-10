package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.InfluencerType;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.InfluencerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/influencerType")
public class InfluencerTypeController {
    private InfluencerTypeService influencerTypeService;

    @Autowired
    public InfluencerTypeController(InfluencerTypeService influencerTypeService) {
        this.influencerTypeService = influencerTypeService;
    }

    @GetMapping
    public ResponseListData<InfluencerType> findAll() {
        return new ResponseListData(influencerTypeService.getAll());
    }
}
