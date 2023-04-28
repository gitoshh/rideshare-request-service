package com.gitoshh.rideshare.RequestService.controller;

import com.gitoshh.rideshare.RequestService.entity.RideRequest;
import com.gitoshh.rideshare.RequestService.request.RideRequestCreateRequest;
import com.gitoshh.rideshare.RequestService.response.RideRequestResponse;
import com.gitoshh.rideshare.RequestService.service.RideRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ride-requests")
public record RideRequestController(RideRequestService rideRequestService) {

    @PostMapping
    public ResponseEntity<RideRequestResponse> createRideRequest(
            @Valid @RequestBody RideRequestCreateRequest rideRequestCreateRequest
    ) {
        return ResponseEntity.ok(rideRequestService.createRideRequest(rideRequestCreateRequest));
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<RideRequest> startRideRequest(@PathVariable Long id) {
        return ResponseEntity.ok(rideRequestService.startRideRequest(id));
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<RideRequest> acceptRideRequest(@PathVariable Long id) {
        return ResponseEntity.ok(rideRequestService.acceptRideRequest(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<RideRequest> rejectRideRequest(@PathVariable Long id) {
        return ResponseEntity.ok(rideRequestService.rejectRideRequest(id));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<RideRequest> cancelRideRequest(@PathVariable Long id) {
        return ResponseEntity.ok(rideRequestService.cancelRideRequest(id));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<RideRequest> completeRideRequest(@PathVariable Long id) {
        return ResponseEntity.ok(rideRequestService.completeRideRequest(id));
    }
}
