package com.InfluencerMarketpalce.serverside.model.request;

public class InfluencerChangePasswordRequest {
    private String oldPassword;
    private String newPassword;

    public InfluencerChangePasswordRequest(){

    }

    public InfluencerChangePasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
