/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Idris Siddiq
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    @Query(value = "SELECT influencer_id FROM tb_user WHERE username = ?1", nativeQuery = true)
    Long findIdByUsername(String username);
    
    @Query(value = "SELECT influencer_id FROM tb_user WHERE user_id = ?1", nativeQuery = true)
    Long findIdById(Long employee_id);
    
    @Query(value = "SELECT * FROM tb_user WHERE influencer_id = ?1", nativeQuery = true)
    User findUsernameById(Long employee_id);
    
    @Query(value = "SELECT COUNT(*) FROM tb_user WHERE username = ?1", nativeQuery = true)
    Long countByUsername(String username);
    
    @Query(value = "SELECT username FROM tb_user WHERE influencer_id = ?1", nativeQuery = true)
    String findUserById(Long id);

    @Query(value = "SELECT * FROM tb_user WHERE username = ?1", nativeQuery = true)
    Optional<User> findAllByUsername(String username);
}
