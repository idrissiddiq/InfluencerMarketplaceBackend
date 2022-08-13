package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignStatusRepository extends JpaRepository<CampaignStatus, Long> {
    @Override
    CampaignStatus getById(Long id);
}
