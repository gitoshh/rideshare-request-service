package com.gitoshh.rideshare.RequestService.repo;

import com.gitoshh.rideshare.RequestService.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
