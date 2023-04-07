/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.request.*;
import com.InfluencerMarketpalce.serverside.model.response.*;
import com.InfluencerMarketpalce.serverside.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Idris Siddiq
 */
@RestController
@RequestMapping("api/register")
public class RegistController {
    private RegistService registService;
    
    @Autowired
    public RegistController(RegistService registService) {
        this.registService = registService;
    }
    
    @PostMapping("/influencer")
    public ResponseMessage<RegisterInfluencerResponse> register(@RequestBody RegisterInfluencerRequest user) {
        return registService.regist(user);
    }

    @PostMapping("/admin")
    public ResponseMessage<RegisterAdminRequest> registerAdmin(@RequestBody RegisterAdminRequest request) {
        return registService.registAdmin(request);
    }
    
    @PutMapping("/influencer/forgot")
    public ResponseMessage<ForgotPasswordRequest> forgot(@RequestBody ForgotPasswordRequest email) {
        System.out.println("Lupa woyy");
        return new ResponseMessage("Password Updated!", registService.forgot(email));
    }

    @PutMapping("/influencer/change")
    public String influencerChangePassword(@RequestBody InfluencerChangePasswordRequest request){
        return registService.influencerChangePassword(request);
    }
}
