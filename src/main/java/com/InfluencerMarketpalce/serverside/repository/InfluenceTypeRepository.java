package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.InfluencerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface InfluenceTypeRepository extends JpaRepository<InfluencerType, Long> {

    Set<InfluencerType> findByName(String name);

    InfluencerType getByName(String name);

    @Query(value = "SELECT type_id FROM tb_influence_type WHERE influencer_id = ?1", nativeQuery = true)
    List<Long> findMyTypeId(Long id);

    @Query(value = "SELECT * FROM tb_influence_type WHERE type_id = ?1", nativeQuery = true)
    InfluencerType getMyTypeName(Long id);

    @Query(value = "SELECT * FROM tb_influence_type WHERE type_id = ?1 AND influencer_id = ?2", nativeQuery = true)
    Set<InfluencerType> getByTypeInfluencerId(Long typeId, Long influencerId);

    @Query(value = "SELECT * FROM tb_influence_type WHERE type_id = ?1 AND influencer_id = ?2", nativeQuery = true)
    Set<Influencer> getByTypeInfluencerId2(Long typeId, Long influencerId);

}
