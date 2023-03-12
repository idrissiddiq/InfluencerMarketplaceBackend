package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.User;
import com.InfluencerMarketpalce.serverside.model.UserBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBrandRepository extends JpaRepository<UserBrand, Long> {
    UserBrand findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM tb_user_brand WHERE username = ?1", nativeQuery = true)
    Long countByUsername(String username);

    @Query(value = "SELECT * FROM tb_user_brand WHERE brand_id = ?1", nativeQuery = true)
    UserBrand findUsernameById(Long brandId);

    @Query(value = "SELECT * FROM tb_user_brand WHERE username = ?1", nativeQuery = true)
    Optional<UserBrand> findAllByUsername(String username);
}
