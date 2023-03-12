package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.BrandFilePath;
import com.InfluencerMarketpalce.serverside.model.InfluencerFilePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandFilePathRepository extends JpaRepository<BrandFilePath, Long> {
    @Query(value = "SELECT * FROM tb_brand_file_path WHERE brand_id = ?1", nativeQuery = true)
    BrandFilePath getMyProfilePhotoPath(Long id);
}
