package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query(value = "SELECT * FROM TB_CAMPAIGN WHERE BRAND_ID = ?1", nativeQuery = true)
    List<Campaign> findAllByBrand(Long id);

    @Query(value = "SELECT * FROM TB_CAMPAIGN WHERE CAMPAIGN_STATUS_ID = ?1", nativeQuery = true)
    List<Campaign> findAllByStatus(Long id);

    @Query(value = "SELECT * FROM TB_CAMPAIGN WHERE BRAND_ID = ?1 AND CAMPAIGN_STATUS_ID = ?2", nativeQuery = true)
    List<Campaign> findAllByBrandStatus(Long brandId, Long statusId);

    @Query(value = "SELECT * FROM TB_CAMPAIGN WHERE BRAND_ID = ?1 AND CAMPAIGN_ID = ?2", nativeQuery = true)
    Optional<Campaign> findAllByBrandId(Long brandId, Long statusId);

}
