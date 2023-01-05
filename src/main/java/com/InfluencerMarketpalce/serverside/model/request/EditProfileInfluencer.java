package com.InfluencerMarketpalce.serverside.model.request;

import java.time.LocalDate;

public class EditProfileInfluencer {
    private String fullname;
    private String email;
    private String city;
    private LocalDate birthDate;
    private String influenceType;

    public EditProfileInfluencer(){

    }

    public EditProfileInfluencer(String fullname, String email, String city, LocalDate birthDate, String influenceType) {
        this.fullname = fullname;
        this.email = email;
        this.city = city;
        this.birthDate = birthDate;
        this.influenceType = influenceType;
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
}
