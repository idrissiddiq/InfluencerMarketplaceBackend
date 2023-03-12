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
    @Query(value = "SELECT * FROM tb_contract WHERE influencer_id = ?1", nativeQuery = true)
    List<Contract> findAllByInfluencer(Long id);

    @Query(value = "SELECT * FROM tb_contract WHERE brand_id = ?1 AND contract_id = ?2", nativeQuery = true)
    Optional<Contract> findAllByBrand(Long brandid, Long contractId);

    @Query(value = "SELECT * FROM tb_contract WHERE influencer_id = ?1 AND contract_id = ?2", nativeQuery = true)
    Optional<Contract> findAllByInfluencerId(Long brandid, Long contractId);

    @Query(value = "SELECT * FROM tb_contract WHERE brand_id = ?1 AND contract_id = ?2", nativeQuery = true)
    Contract findByBrand(Long brandid, Long contractId);

    @Query(value = "SELECT * FROM tb_contract WHERE influencer_id = ?1 AND contract_id = ?2", nativeQuery = true)
    Contract findByInfluencer(Long influencerId, Long contractId);

    @Query(value = "SELECT * FROM tb_contract WHERE contract_id = ?1", nativeQuery = true)
    Contract findContractById(Long contractId);

    @Query(value = "SELECT * FROM tb_contract WHERE brand_id = ?1 AND campaign_id = ?2 ", nativeQuery = true)
    List<Contract> findAllByBrandCampaign(Long brandId, Long campaignId);

    @Query(value = "SELECT * FROM tb_contract WHERE brand_id = ?1 AND campaign_id = ?2 AND contract_status_id = ?3", nativeQuery = true)
    List<Contract> findAllByBrandCampaignStatus(Long brandId, Long campaignId, Long statusId);
}
