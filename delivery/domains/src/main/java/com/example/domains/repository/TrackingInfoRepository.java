package com.example.domains.repository;

import com.example.domains.domain.TrackingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TrackingInfoRepository extends JpaRepository<TrackingInfo,Integer> {
}
