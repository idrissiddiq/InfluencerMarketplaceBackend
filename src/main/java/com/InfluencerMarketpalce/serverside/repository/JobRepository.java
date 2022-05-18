/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Job;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository 
public interface JobRepository extends JpaRepository<Job,String>{
    @Query(value = "SELECT * FROM TB_JOB WHERE JOB_ID = ?1", nativeQuery = true)
    Job findByIdJob(String id);
    
    @Query(value = "SELECT * FROM TB_JOB WHERE JOB_ID != 'A'", nativeQuery = true)
    List<Job> findAllExceptAdmin();
}
