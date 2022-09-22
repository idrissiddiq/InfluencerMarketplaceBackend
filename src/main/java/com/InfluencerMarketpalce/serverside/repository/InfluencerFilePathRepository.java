package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.ContractStatus;
import com.InfluencerMarketpalce.serverside.model.InfluencerFilePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfluencerFilePathRepository extends JpaRepository<InfluencerFilePath, Long> {
    @Query(value = "SELECT * FROM TB_INFLUENCER_FILE_PATH WHERE INFLUENCER_ID = ?1", nativeQuery = true)
    InfluencerFilePath getMyProfilePhotoPath(Long id);
}
