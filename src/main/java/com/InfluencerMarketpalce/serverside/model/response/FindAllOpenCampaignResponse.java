package com.InfluencerMarketpalce.serverside.model.response;

public interface FindAllOpenCampaignResponse {
    Long getId();
    String getTitle();
    String getDescription();
    String getCompany();
    String getName();
    Long getStartIg();
    Long getEndIg();
    Long getStartTiktok();
    Long getEndTiktok();
    Long getStartYt();
    Long getEndYt();
}
