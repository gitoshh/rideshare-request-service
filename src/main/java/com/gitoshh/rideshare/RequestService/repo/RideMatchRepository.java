package com.gitoshh.rideshare.RequestService.repo;

import com.gitoshh.rideshare.RequestService.entity.RideMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RideMatchRepository extends JpaRepository<RideMatch, Long> {
    @Query("SELECT rm FROM RideMatch rm WHERE rm.driverId = ?1")
    List<RideMatch> findByDriverId(Long riderId);
}
