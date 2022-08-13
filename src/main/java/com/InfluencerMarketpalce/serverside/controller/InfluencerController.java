/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.ChangePasswordDTO;
import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.service.InfluencerService;
import com.InfluencerMarketpalce.serverside.model.response.EmployeeRequest;
import com.InfluencerMarketpalce.serverside.model.response.ProfileDTO;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    @GetMapping("/except")
//    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    public ResponseListData<Influencer> findAllExcept() {
        return new ResponseListData(influencerService.findAllExcept());
    }

    @GetMapping("/findAllSortByRate")
    public ResponseListData<Influencer> findAllSortByRate() {
        return new ResponseListData(influencerService.findAllSortByRate());
    }
    
//    @PostMapping("/emailcheck")
//    public ResponseMessage findEmail(@RequestBody ForgotPasswordRequest email){
//        return new ResponseMessage("Error - Email Already Exist",employeeService.findEmail(email));
//    }
    
    @GetMapping("/class/peserta/{id}")
    public ResponseListData<Influencer> findEmployeeByClass(@PathVariable String id) {
        return new ResponseListData(influencerService.findEmployeeByClassId(id));
    }

    @GetMapping("/available_peserta")
    public ResponseListData<Influencer> findEmployeeByClass() {
        return new ResponseListData(influencerService.findAllEmployeeWithNoClass());
    }

    @GetMapping("/all_trainer")
    public ResponseListData<Influencer> findAllTrainer() {
        return new ResponseListData(influencerService.findAllTrainer());
    }

    @GetMapping("/profile/{id}")
    public ResponseData<ProfileDTO> getUserProfileInfo(@PathVariable("id") String id) {
        return new ResponseData(influencerService.findUserProfileInfo(id));
    }

    @GetMapping("/{id}")
    public ResponseData<Influencer> findById(@PathVariable Long id) {
        return new ResponseData(influencerService.findById(id));
    }

    @PostMapping
    public ResponseMessage<EmployeeRequest> create(@RequestBody EmployeeRequest request) {
        return influencerService.create(request);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Influencer> delete(@PathVariable Long id) {
        return new ResponseMessage("Employee Deleted!", influencerService.delete(id));
    }

    @DeleteMapping("/remove_peserta/{classId}/{id}")
    public ResponseMessage<Influencer> removeClass(@PathVariable("classId") String classId, @PathVariable("id") Long id) {
        return new ResponseMessage("Peserta Removed!", influencerService.removeEmployeeFromClass(id, classId));
    }

    @DeleteMapping("/remove_trainer/{id}/{employeeId}")
    public ResponseMessage<Influencer> removeTrainer(@PathVariable("id") Long id, @PathVariable("employeeId") Long employeeId) {
        return new ResponseMessage("Trainer Removed!", influencerService.removeTrainerFromClass(id, employeeId));
    }

    @PutMapping("/{id}")
    public ResponseMessage<EmployeeRequest> update(@RequestBody EmployeeRequest employee, @PathVariable Long id) {
        return new ResponseMessage("Employee Updated!", influencerService.update(employee, id));
    }

    @PutMapping("change_password/{username}")
    public ResponseMessage<Influencer> changePassword(@RequestBody ChangePasswordDTO employee, @PathVariable String username) {
        return new ResponseMessage("Password Updated!", influencerService.changePassword(employee, username));
    }

}
