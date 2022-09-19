/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileInfluencer;
import com.InfluencerMarketpalce.serverside.service.InfluencerService;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/influencer")
public class InfluencerController {

    private InfluencerService influencerService;

    @Autowired
    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    @GetMapping
    public ResponseListData<Influencer> findAll() {
        return new ResponseListData(influencerService.findAll());
    }

    @GetMapping("/profile")
    public ResponseData<Influencer> profile() {
        return new ResponseData(influencerService.profile());
    }

    @PutMapping("/profile")
    public ResponseMessage editProfile(@RequestBody EditProfileInfluencer request){
        return influencerService.editProfile(request);
    }

    @GetMapping("/except")
//    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    public ResponseListData<Influencer> findAllExcept() {
        return new ResponseListData(influencerService.findAllExcept());
    }

    @GetMapping("/findAllSortByRate")
    public ResponseListData<Influencer> findAllSortByRate() {
        return new ResponseListData(influencerService.findAllSortByRate());
    }

}
