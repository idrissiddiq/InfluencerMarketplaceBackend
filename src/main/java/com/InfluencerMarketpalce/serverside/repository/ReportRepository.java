package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(value = "SELECT * FROM tb_report WHERE view IS null AND influencer_id = ?1 AND DATE(create_date) <= CURDATE() - INTERVAL 30 DAY", nativeQuery = true)
    List<Report> findAllMonthlyReportbyInfluencer(Long id);

    @Query(value = "SELECT * FROM tb_report WHERE brand_id = ?1", nativeQuery = true)
    List<Report> findAllReportbyBrand(Long id);
}
