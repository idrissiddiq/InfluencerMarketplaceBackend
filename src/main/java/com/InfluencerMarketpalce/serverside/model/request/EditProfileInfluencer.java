package com.InfluencerMarketpalce.serverside.model.request;

import java.time.LocalDate;

public class EditProfileInfluencer {
    private String fullname;
    private String email;
    private String city;
    private LocalDate birthDate;
    private Long province;
    private String detailAddress;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String facebook;


    public EditProfileInfluencer(){

    }

    public EditProfileInfluencer(String fullname, String email, String city, LocalDate birthDate, Long province, String detailAddress, String instagram, String youtube, String tiktok, String facebook) {
        this.fullname = fullname;
        this.email = email;
        this.city = city;
        this.birthDate = birthDate;
        this.province = province;
        this.detailAddress = detailAddress;
        this.instagram = instagram;
        this.youtube = youtube;
        this.tiktok = tiktok;
        this.facebook = facebook;
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
