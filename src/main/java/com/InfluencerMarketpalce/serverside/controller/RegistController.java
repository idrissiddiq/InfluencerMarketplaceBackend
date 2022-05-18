/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.service.RegistService;
import com.InfluencerMarketpalce.serverside.model.response.ForgotPasswordRequest;
import com.InfluencerMarketpalce.serverside.model.response.RegisterEmployeeRequest;
import com.InfluencerMarketpalce.serverside.model.response.RegisterEmployeeResponse;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
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
@RequestMapping("api/employee/register")
public class RegistController {
    private RegistService registService;
    
    @Autowired
    public RegistController(RegistService registService) {
        this.registService = registService;
    }
    
    @PostMapping()
    public ResponseMessage<RegisterEmployeeRequest> register(@RequestBody RegisterEmployeeRequest user) {
        return registService.regist(user);
    }

//    @PutMapping("setPassword/{username}")
//    public ResponseEntity<User> setPassword(@PathVariable String username, @RequestBody String pass) {
//        return new ResponseEntity(registerService.setPassword(pass, username), HttpStatus.OK);
//    }
    
    @PutMapping("/{id}")
    public ResponseMessage<RegisterEmployeeRequest> update(@RequestBody RegisterEmployeeRequest employee, @PathVariable Long id) {
        return new ResponseMessage("Employee Updated!", registService.update(employee,id));
    }
    
    @PostMapping("/forgot")
    public ResponseMessage<ForgotPasswordRequest> forgot(@RequestBody ForgotPasswordRequest email) {
        System.out.println("Lupa woyy");
        return new ResponseMessage("Password Updated!", registService.forgot(email));
    }
    
    @GetMapping("/{id}")
    public ResponseData<RegisterEmployeeResponse> findById(@PathVariable Long id) {
        return new ResponseData(registService.getById(id));
    }
}
