package com.InfluencerMarketpalce.serverside.model.request;

public class ChangeProfilePhotoRequest {
    private String path;

    public ChangeProfilePhotoRequest(){

    }

    public ChangeProfilePhotoRequest(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
