package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.InfluencerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface InfluenceTypeRepository extends JpaRepository<InfluencerType, Long> {

    Set<InfluencerType> findByName(String name);

    @Query(value = "SELECT TYPE_ID FROM INFLUENCER_TYPE WHERE INFLUENCER_ID = ?1", nativeQuery = true)
    List<Long> findMyTypeId(Long id);

    @Query(value = "SELECT * FROM TB_INFLUENCE_TYPE WHERE TYPE_ID = ?1", nativeQuery = true)
    InfluencerType getMyTypeName(Long id);

}
