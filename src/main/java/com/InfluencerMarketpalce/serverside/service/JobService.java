/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.Job;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import com.InfluencerMarketpalce.serverside.repository.JobRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class JobService extends ResponseStatus {
    private JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    public List<Job> findAll() {
        return jobRepository.findAll();
    }
    
    public List<Job> findAllExcept() {
        return jobRepository.findAllExceptAdmin();
    }

    public Job findById(String id) {
        Job data = jobRepository.findById(id).orElseThrow(this::dataNotFound);
        return data;
    }

    public Job create(Job job) {
        return jobRepository.save(job);
    }

    public Job delete(String id) {
        Job data = jobRepository.findById(id).orElseThrow(this::dataNotFound);
        jobRepository.deleteById(id);
        return data;
    }

    public Job update(Job job, String id) {
        Optional<Job> temp = jobRepository.findById(id);
        if (!temp.isPresent()) {
            throw dataNotFound();
        }
        return jobRepository.save(job);
    }
}
