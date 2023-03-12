package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.UserAdmin;
import com.InfluencerMarketpalce.serverside.model.UserBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {
    UserAdmin findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM tb_user_admin WHERE username = ?1", nativeQuery = true)
    Long countByUsername(String username);
}
