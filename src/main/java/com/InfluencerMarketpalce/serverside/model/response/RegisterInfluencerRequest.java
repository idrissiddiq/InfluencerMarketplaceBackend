/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.model.response;

/**
 *
 * @author Sendy
 */
public class RegisterInfluencerRequest {
    private Long id;

    private String fullname;

    private String email;

    private String jobId;
    private String username;
    private String password;

    public RegisterInfluencerRequest() {
    }

    public RegisterInfluencerRequest(Long id, String fullname, String email, String jobId, String username, String password) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.jobId = jobId;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    
}
