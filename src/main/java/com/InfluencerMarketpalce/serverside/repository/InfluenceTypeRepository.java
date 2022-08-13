package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.InfluenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InfluenceTypeRepository extends JpaRepository<InfluenceType, Long> {

    Set<InfluenceType> findByName(String name);

}
