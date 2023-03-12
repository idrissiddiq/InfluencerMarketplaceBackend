package com.InfluencerMarketpalce.serverside.model.request;

public class UpdateCampaignRequest {
    String title;
    String description;
    Long campaignStatus;

    public UpdateCampaignRequest(){

    }

    public UpdateCampaignRequest(String title, String description, Long campaignStatus) {
        this.title = title;
        this.description = description;
        this.campaignStatus = campaignStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getcampaignStatus() {
        return campaignStatus;
    }

    public void setcampaignStatus(Long campaignStatus) {
        this.campaignStatus = campaignStatus;
    }
}
