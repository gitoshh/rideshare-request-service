package com.gitoshh.rideshare.RequestService.service;


import com.gitoshh.rideshare.RequestService.entity.RideMatch;
import com.gitoshh.rideshare.RequestService.entity.RideMatchStatus;
import com.gitoshh.rideshare.RequestService.entity.RideRequest;
import com.gitoshh.rideshare.RequestService.exception.NotFoundException;
import com.gitoshh.rideshare.RequestService.repo.RideMatchRepository;
import com.gitoshh.rideshare.RequestService.repo.RideRequestRepository;
import com.gitoshh.rideshare.RequestService.types.RideRequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RideMatchService {

    private final RideMatchRepository rideMatchRepository;

    private final RideRequestRepository rideRequestRepository;

    public List<RideMatch> getRideMatchesByUserId(Long riderId) {
        return rideMatchRepository.findByDriverId(riderId);
    }

    public RideMatch acceptRideMatch(Long id) {
        RideMatch rideMatch = rideMatchRepository.findById(id).orElseThrow(() -> new NotFoundException("Ride match not found"));
        rideMatch.accept();
        rideMatchRepository.save(rideMatch);

        // Set the ride request as accepted
        RideRequest rideRequest = rideRequestRepository.findById(rideMatch.getRideRequestId()).orElseThrow(()-> new NotFoundException("Ride request not found"));
        rideRequest.accept();
        rideRequestRepository.save(rideRequest);

        return rideMatch;
    }

    public RideMatch rejectRideMatch(Long id) {
        RideMatch rideMatch = rideMatchRepository.findById(id).orElseThrow(() -> new NotFoundException("Ride match not found"));
        rideMatch.reject();
        rideMatchRepository.save(rideMatch);

        // Set the ride request as accepted
        RideRequest rideRequest = rideRequestRepository.findById(rideMatch.getRideRequestId()).orElseThrow(()-> new NotFoundException("Ride request not found"));
        rideRequest.reject();
        rideRequestRepository.save(rideRequest);

        return rideMatch;
    }


}
