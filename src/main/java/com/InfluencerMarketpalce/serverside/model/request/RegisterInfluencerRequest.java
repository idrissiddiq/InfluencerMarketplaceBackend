/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.model.request;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 *
 * @author Sendy
 */
public class RegisterInfluencerRequest {
    private Long id;
    private String fullname;
    private String email;
    private String city;
    private LocalDate birthDate;
    private String username;
    private Long province;
    private String detailAddress;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String facebook;

    public RegisterInfluencerRequest(Long id, String fullname, String email, String city, LocalDate birthDate, String username, Long province, String detailAddress, String instagram, String youtube, String tiktok, String facebook) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.city = city;
        this.birthDate = birthDate;
        this.username = username;
        this.province = province;
        this.detailAddress = detailAddress;
        this.instagram = instagram;
        this.youtube = youtube;
        this.tiktok = tiktok;
        this.facebook = facebook;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
