package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value = "SELECT * FROM TB_CONTRACT WHERE INFLUENCER_ID = ?1", nativeQuery = true)
    List<Contract> findAllByInfluencer(Long id);

    @Query(value = "SELECT * FROM TB_CONTRACT WHERE BRAND_ID = ?1 AND CONTRACT_ID = ?2", nativeQuery = true)
    Optional<Contract> findAllByBrand(Long brandid, Long contractId);

    @Query(value = "SELECT * FROM TB_CONTRACT WHERE BRAND_ID = ?1 AND CONTRACT_ID = ?2", nativeQuery = true)
    Contract findByBrand(Long brandid, Long contractId);

    @Query(value = "SELECT * FROM TB_CONTRACT WHERE BRAND_ID = ?1 AND CAMPAIGN_ID = ?2 ", nativeQuery = true)
    List<Contract> findAllByBrandCampaign(Long brandId, Long campaignId);

    @Query(value = "SELECT * FROM TB_CONTRACT WHERE BRAND_ID = ?1 AND CAMPAIGN_ID = ?2 AND CONTRACT_STATUS_ID = ?3", nativeQuery = true)
    List<Contract> findAllByBrandCampaignStatus(Long brandId, Long campaignId, Long statusId);
}
