package com.gitoshh.rideshare.RequestService.controller;

import com.gitoshh.rideshare.RequestService.entity.RideMatch;
import com.gitoshh.rideshare.RequestService.service.RideMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/ride-matches")
@RequiredArgsConstructor
public class RideMatchController {
    private final RideMatchService rideMatchService;

    @GetMapping("/driver/{id}")
    public ResponseEntity<List<RideMatch>> getRideMatchesByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(rideMatchService.getRideMatchesByUserId(id));
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<RideMatch> acceptRideMatch(@PathVariable Long id) {
        return ResponseEntity.ok(rideMatchService.acceptRideMatch(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<RideMatch> rejectRideMatch(@PathVariable Long id) {
        return ResponseEntity.ok(rideMatchService.rejectRideMatch(id));
    }
}
