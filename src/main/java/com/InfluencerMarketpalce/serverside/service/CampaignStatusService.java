package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.CampaignStatus;
import com.InfluencerMarketpalce.serverside.repository.CampaignStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignStatusService {
    private CampaignStatusRepository campaignStatusRepository;

    @Autowired
    public CampaignStatusService(CampaignStatusRepository campaignStatusRepository) {
        this.campaignStatusRepository = campaignStatusRepository;
    }

    public List<CampaignStatus> findAll() {
        return campaignStatusRepository.findAll();
    }
}
