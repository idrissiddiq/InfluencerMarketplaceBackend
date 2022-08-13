/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.model.response;

import java.time.LocalDate;

/**
 *
 * @author Sendy
 */
public class RegisterInfluencerRequest {
    private Long id;

    private String fullname;

    private String email;

    private String jobId;
    private String city;
    private LocalDate birthDate;
    private String influenceType;
    private String username;


    public RegisterInfluencerRequest(Long id, String fullname, String email, String jobId, String city, LocalDate birthDate, String influenceType, String username) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.jobId = jobId;
        this.city = city;
        this.birthDate = birthDate;
        this.influenceType = influenceType;
        this.username = username;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getInfluenceType() {
        return influenceType;
    }

    public void setInfluenceType(String influenceType) {
        this.influenceType = influenceType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
