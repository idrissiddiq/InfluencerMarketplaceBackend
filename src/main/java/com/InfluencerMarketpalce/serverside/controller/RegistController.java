/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.request.ForgotPasswordRequest;
import com.InfluencerMarketpalce.serverside.model.request.InfluencerChangePasswordRequest;
import com.InfluencerMarketpalce.serverside.model.request.RegisterBrandRequest;
import com.InfluencerMarketpalce.serverside.model.request.RegisterInfluencerRequest;
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

    @PostMapping("/brand")
    public ResponseMessage<RegisterBrandRequest> registerBrand(@RequestBody RegisterBrandRequest userBrand) {
        return registService.registBrand(userBrand);
    }
    
    @PutMapping("/influencer/forgot")
    public ResponseMessage<ForgotPasswordRequest> forgot(@RequestBody ForgotPasswordRequest email) {
        System.out.println("Lupa woyy");
        return new ResponseMessage("Password Updated!", registService.forgot(email));
    }

    @PutMapping("/brand/forgot")
    public ResponseMessage<ForgotPasswordRequest> forgotBrand(@RequestBody ForgotPasswordRequest email) {
        System.out.println("Lupa woyy");
        return new ResponseMessage("Password Updated!", registService.forgotBrand(email));
    }

    @PutMapping("/influencer/change")
    public String influencerChangePassword(@RequestBody InfluencerChangePasswordRequest request){
        return registService.influencerChangePassword(request);
    }
}
