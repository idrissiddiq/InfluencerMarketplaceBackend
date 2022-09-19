package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Brand;
import com.InfluencerMarketpalce.serverside.model.Campaign;
import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
    @Query(value = "SELECT COUNT(*) FROM TB_BRAND WHERE EMAIL = ?1", nativeQuery = true)
    Long findEmail(String email);

    @Query(value = "SELECT BRAND_ID FROM TB_BRAND WHERE EMAIL = ?1", nativeQuery = true)
    Long findIdByEmail (String email);

    @Query(value = "SELECT * FROM TB_BRAND WHERE EMAIL = ?1", nativeQuery = true)
    Optional<Brand> findAllByEmail(String email);

    Brand getById(Long id);
}
