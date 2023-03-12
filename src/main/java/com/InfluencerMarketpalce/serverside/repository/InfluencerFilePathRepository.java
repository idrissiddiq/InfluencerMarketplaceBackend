package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.ContractStatus;
import com.InfluencerMarketpalce.serverside.model.InfluencerFilePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfluencerFilePathRepository extends JpaRepository<InfluencerFilePath, Long> {
    @Query(value = "SELECT * FROM tb_influencer_file_path WHERE influencer_id = ?1", nativeQuery = true)
    InfluencerFilePath getMyProfilePhotoPath(Long id);
}
