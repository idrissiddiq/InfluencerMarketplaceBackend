/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.response.CalculateAgeReponse;
import com.InfluencerMarketpalce.serverside.model.response.FindAllInfluencerResponse;
import com.InfluencerMarketpalce.serverside.model.response.ProfileDTO;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

    @Query(value = "SELECT * FROM tb_influencer WHERE job_id != 'A'", nativeQuery = true)
    List<Influencer> findAllExceptAdmin();

    @Query(value = "SELECT tb_influencer.fullname as \\\"fullname\\\", tb_influencer.city as \\\"city\\\", tb_influencer.engagement_rate as \\\"er\\\", tb_influencer.rate as \\\"rate\\\", (tb_influencer.rate / tb_influencer.campaign_done) as \\\"finalrate\\\", tb_user.username as \\\"username\\\", TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) as \\\"age\\\", tb_influencer.instagram as \\\"instagram\\\", tb_influencer.tiktok as \\\"tiktok\\\", tb_influencer.youtube as \\\"youtube\\\" FROM tb_influencer INNER JOIN tb_user ON tb_influencer.influencer_id=tb_user.influencer_id ORDER BY RATE DESC", nativeQuery = true)
    List<FindAllInfluencerResponse> findAllInfluencer();

    @Query(value = "SELECT * FROM tb_influencer ORDER BY rate DESC", nativeQuery = true)
    List<Influencer> findAllSortByRate();

    @Query(value = "SELECT TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) as \\\"age\\\" FROM tb_influencer ORDER BY RATE DESC", nativeQuery = true)
    List<CalculateAgeReponse> findAgeSortByRate();

    @Query(value = "SELECT COUNT(*) FROM tb_influencer WHERE email = ?1", nativeQuery = true)
    Long findEmail(String email);

    @Query(value = "SELECT * FROM tb_influencer WHERE email = ?1", nativeQuery = true)
    Optional<Influencer> findAllByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_user SET password = :password where username = :username",
            nativeQuery = true)
    void changePassword(@Param("password") String password,
            @Param("username") String username);

    @Query(value = "SELECT password FROM tb_user WHERE username =? 1", nativeQuery = true)
    String getPassword(String username);

    @Query(value = "SELECT influencer_id FROM tb_influencer WHERE email = ?1", nativeQuery = true)
    Long findIdByEmail (String email);
    
    @Query(value = "SELECT email FROM tb_influencer WHERE user_id = ?1", nativeQuery = true)
    String findEmailById (Long id);
}
