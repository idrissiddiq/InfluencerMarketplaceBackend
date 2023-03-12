package com.InfluencerMarketpalce.serverside.model.response;

public class GetProfilePhotoByIdResponse {
    private String path;

    public GetProfilePhotoByIdResponse(){

    }

    public GetProfilePhotoByIdResponse(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
