package com.InfluencerMarketpalce.serverside.model.response;

import com.InfluencerMarketpalce.serverside.model.Job;

public class RegisterBrandResponse {
    private Long id;

    private String fullname;

    private String email;

    private Job job;
    private String username;
    private String password;

    public RegisterBrandResponse(){

    }

    public RegisterBrandResponse(Long id, String fullname, String email, Job job, String username, String password) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.job = job;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public Job getJob() {
        return job;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
