/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Job;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/job")
//@PreAuthorize("hasRole('ADMIN')")
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    
    @GetMapping
    public ResponseListData<Job> findAll() {
        return new ResponseListData(jobService.findAll());
    }
    
    @GetMapping("/except")
    public ResponseListData<Job> findAllexcept() {
        return new ResponseListData(jobService.findAllExcept());
    }
    
    @GetMapping("/{id}")
    public ResponseData<Job> findById(@PathVariable String id) {
        return new ResponseData(jobService.findById(id));
    }

    @PostMapping
    public ResponseData<Job> create(@RequestBody Job job) {
        return new ResponseData(jobService.create(job));
    }
    
    @DeleteMapping("/{id}")
    public ResponseData<Job> delete(@PathVariable String id) {
        return new ResponseData(jobService.delete(id));
    }
    
    @PutMapping("/{id}")
    public ResponseData<Job> update(@RequestBody Job job, @PathVariable String id) {
        return new ResponseData(jobService.update(job,id));
    }
}
