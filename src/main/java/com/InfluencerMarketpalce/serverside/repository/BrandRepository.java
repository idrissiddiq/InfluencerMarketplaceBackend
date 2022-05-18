package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
    @Query(value = "SELECT COUNT(*) FROM TB_BRAND WHERE EMAIL = ?1", nativeQuery = true)
    Long findEmail(String email);
}
