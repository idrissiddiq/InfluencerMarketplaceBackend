package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.User;
import com.InfluencerMarketpalce.serverside.model.UserBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBrandRepository extends JpaRepository<UserBrand, Long> {
    UserBrand findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM TB_USER_BRAND WHERE USERNAME = ?1", nativeQuery = true)
    Long countByUsername(String username);
}
