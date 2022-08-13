package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.CampaignStatus;
import com.InfluencerMarketpalce.serverside.model.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractStatusRepository extends JpaRepository<ContractStatus, Long> {
    @Override
    ContractStatus getById(Long id);
}
