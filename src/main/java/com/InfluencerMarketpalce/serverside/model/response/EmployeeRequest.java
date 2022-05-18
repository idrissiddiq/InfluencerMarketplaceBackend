/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.model.response;

/**
 *
 * @author Idris Siddiq
 */
public class EmployeeRequest {
    private Long id;

    private String fullname;

    private String email;

    private String jobId;
    
    public EmployeeRequest(){
        
    }
    
    public EmployeeRequest(Long id, String fullname, String email, String jobId) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.jobId = jobId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    

}
