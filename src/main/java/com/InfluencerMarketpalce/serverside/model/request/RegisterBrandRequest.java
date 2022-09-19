package com.InfluencerMarketpalce.serverside.model.request;

public class RegisterBrandRequest {
    private Long id;

    private String fullname;

    private String email;
    private String username;

    public RegisterBrandRequest() {
    }

    public RegisterBrandRequest(Long id, String fullname, String email, String username) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
