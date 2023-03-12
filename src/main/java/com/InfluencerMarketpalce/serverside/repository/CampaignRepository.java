package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.response.FindAllInfluencerResponse;
import com.InfluencerMarketpalce.serverside.model.response.FindAllOpenCampaignResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query(value = "SELECT * FROM tb_campaign WHERE brand_id = ?1", nativeQuery = true)
    List<Campaign> findAllByBrand(Long id);

    @Query(value = "SELECT * FROM tb_campaign WHERE campaign_status_id = ?1", nativeQuery = true)
    List<Campaign> findAllByStatus(Long id);

    @Query(value = "SELECT * FROM tb_campaign WHERE brand_id = ?1 AND campaign_status_id = ?2", nativeQuery = true)
    List<Campaign> findAllByBrandStatus(Long brandId, Long statusId);

    @Query(value = "SELECT * FROM tb_campaign WHERE brand_id = ?1 AND campaign_id = ?2", nativeQuery = true)
    Optional<Campaign> findAllByBrandId(Long brandId, Long statusId);

    @Query(value = "SELECT tb_campaign.campaign_id as \\\"id\\\", tb_campaign.title as \\\"title\\\", tb_campaign.description as \\\"description\\\", tb_brand.fullname as \\\"name\\\", tb_brand.company as \\\"company\\\", tb_campaign.budget as \\\"budget\\\" FROM tb_campaign INNER JOIN tb_brand ON tb_campaign.brand_id=tb_brand.brand_id where tb_campaign.CAMPAIGN_STATUS_ID = 1", nativeQuery = true)
    List<FindAllOpenCampaignResponse> findAllCampaignOpen();

}
