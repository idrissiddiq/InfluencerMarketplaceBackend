/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.request.LoginRequestDto;
import com.InfluencerMarketpalce.serverside.model.response.LoginResponseDto;
import com.InfluencerMarketpalce.serverside.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @PostMapping("/influencer")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest){
        System.out.println(loginRequest.getUsername());
        return new ResponseEntity(loginService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/brand")
    public ResponseEntity<LoginResponseDto> loginBrand(@RequestBody LoginRequestDto loginRequest){
        System.out.println(loginRequest.getUsername());
        return new ResponseEntity(loginService.loginBrand(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<LoginResponseDto> loginAdmin(@RequestBody LoginRequestDto loginRequest){
        System.out.println(loginRequest.getUsername());
        return new ResponseEntity(loginService.loginAdmin(loginRequest), HttpStatus.OK);
    }
}
