package com.InfluencerMarketpalce.serverside.repository;

import com.InfluencerMarketpalce.serverside.model.Contract;
import com.InfluencerMarketpalce.serverside.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT * FROM tb_notification WHERE influencer_id = ?1 LIMIT 5", nativeQuery = true)
    List<Notification> findAllByInfluencer(Long id);

    @Query(value = "SELECT * FROM tb_notification WHERE influencer_id = ?1 LIMIT 100", nativeQuery = true)
    List<Notification> findAllByInfluencerExpanded(Long id);

    @Query(value = "SELECT count(*) FROM tb_notification WHERE influencer_id = ?1 AND status = 'U'", nativeQuery = true)
    Long checkReadByInfluencer(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_notification SET status = 'R' where influencer_id = :id",
            nativeQuery = true)
    void updateStatusToRead(@Param("id") Long id);
}
